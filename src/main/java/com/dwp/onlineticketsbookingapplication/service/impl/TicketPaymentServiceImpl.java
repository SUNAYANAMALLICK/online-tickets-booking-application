package com.dwp.onlineticketsbookingapplication.service.impl;

import com.dwp.onlineticketsbookingapplication.entity.TicketsPayment;
import com.dwp.onlineticketsbookingapplication.entity.UserAccounts;
import com.dwp.onlineticketsbookingapplication.enums.TicketTypesEnum;
import com.dwp.onlineticketsbookingapplication.errorhandle.ErrorModel;
import com.dwp.onlineticketsbookingapplication.exception.TicketBookingException;
import com.dwp.onlineticketsbookingapplication.model.Request;
import com.dwp.onlineticketsbookingapplication.model.Response;
import com.dwp.onlineticketsbookingapplication.model.TicketsTypeModel;
import com.dwp.onlineticketsbookingapplication.repository.TicketPaymentRepository;
import com.dwp.onlineticketsbookingapplication.service.TicketPaymentService;
import com.dwp.onlineticketsbookingapplication.service.TicketsTypeService;
import com.dwp.onlineticketsbookingapplication.service.UserAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
public class TicketPaymentServiceImpl implements TicketPaymentService {

    @Autowired
    UserAccountsService userAccountsService;

    @Autowired
    TicketsTypeService ticketsTypeService;

    @Autowired
    TicketPaymentRepository ticketPaymentRepository;

    @Autowired
    SeatReservationServiceImpl seatReservationService;

    @Override
    public ResponseEntity<Object> processRequest(Request request) throws TicketBookingException {

        final UserAccounts userAccounts = userAccountsService.checkForValidUser(request.getEmailId());

        // validate if total no of tickets is less than equals to 20
        ResponseEntity<Object> response = validateForTotalNoOfTickets(request);
        if(null!= response){
            return response;
        }

        // validate if infant or children tickets getting purchased with adult tickets
        if(request.getAdultCount() ==0 && (request.getChildCount() + request.getInfantCount() > 0)){
            ErrorModel errorModel = new ErrorModel();
            errorModel.setErrMessage("Infant or children tickets getting purchased with adult tickets.");
            errorModel.setErrorCode(INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(errorModel,INTERNAL_SERVER_ERROR);
        }

        // create map with pre-defined cost in Tickets Type
        final  HashMap<TicketTypesEnum, Double>  requestMap =  updateMapWithPreDefinedCostInTicketsType(request);

        // validate the funds available to purchase the tickets

        // payment update
        TicketsPayment ticketsPayment = calculateTotalCostAndProcessPayment(userAccounts, requestMap);

        // update payment in user account
        updateUserAccountsWithPaymentInfo(userAccounts, ticketsPayment);

        // Seat Reservation
        return getReservation(request.getChildCount(), request.getAdultCount(), ticketsPayment);
    }

    private  HashMap<TicketTypesEnum, Double> updateMapWithPreDefinedCostInTicketsType(final Request request) {
        final HashMap<TicketTypesEnum, Double> mapRequest = new HashMap<>();
        // calculate total cost
        List<TicketsTypeModel> ticketsTypeModels = ticketsTypeService.getAllTicketsType();
        ticketsTypeModels.stream().forEach(value->{
            if(value.getType().equals(TicketTypesEnum.ADULT)){
                mapRequest.put(TicketTypesEnum.ADULT, request.getAdultCount() * value.getPrice());
            }
            if(value.getType().equals(TicketTypesEnum.CHILD)){
                mapRequest.put(TicketTypesEnum.CHILD, request.getChildCount() * value.getPrice());
            }
            if(value.getType().equals(TicketTypesEnum.INFANT)){
                mapRequest.put(TicketTypesEnum.INFANT, request.getChildCount() * value.getPrice());
            }
        });
        return mapRequest;
    }

    private TicketsPayment calculateTotalCostAndProcessPayment(UserAccounts userAccounts, Map<TicketTypesEnum, Double> requestMap) {
        TicketsPayment ticketsPayment = processPayment(userAccounts.getId(),
                requestMap.values().stream().mapToInt(Double::intValue).sum());
        return ticketsPayment;
    }

    private void updateUserAccountsWithPaymentInfo(UserAccounts userAccounts, TicketsPayment ticketsPayment) {
        userAccounts.setTotalCredit(userAccounts.getTotalCredit()+ ticketsPayment.getAmount());
        userAccounts.getRequestIds().add(ticketsPayment.getId());
        userAccountsService.addOrUpdateUserAccounts(userAccounts);
    }

    private ResponseEntity<Object> getReservation(final Integer childCount,final  Integer adultCount, final TicketsPayment ticketsPayment) {
        // Ensure that Infants are not allocated a seat
        List<String> generatedSeat = seatReservationService.generateSeatNumber((childCount + adultCount), ticketsPayment.getId());
        final Response response = new Response(ticketsPayment.getId(), generatedSeat, ticketsPayment.getAmount());
        return new ResponseEntity<>(response,CREATED);
    }

    private static ResponseEntity<Object> validateForTotalNoOfTickets(final Request request) throws TicketBookingException {
        if(request.getAdultCount() + request.getChildCount() + request.getInfantCount() >20){
            ErrorModel errorModel = new ErrorModel();
            errorModel.setErrMessage("Total no of tickets limit is exceed. Maximum limit is 20.");
            errorModel.setErrorCode(INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(errorModel,INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    /**
     * @param
     * @return
     */
    @Override
    public TicketsPayment processPayment(final Long userAccountId, final long amount) {
        TicketsPayment ticketsPayment = new TicketsPayment();
        ticketsPayment.setUser(userAccountId);
        ticketsPayment.setStatus("Done");
        ticketsPayment.setAmount(amount);
        return ticketPaymentRepository.save(ticketsPayment);
    }

}
