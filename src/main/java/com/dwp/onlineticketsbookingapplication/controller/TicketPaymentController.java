package com.dwp.onlineticketsbookingapplication.controller;

import com.dwp.onlineticketsbookingapplication.exception.TicketBookingException;
import com.dwp.onlineticketsbookingapplication.model.Request;
import com.dwp.onlineticketsbookingapplication.model.Response;
import com.dwp.onlineticketsbookingapplication.service.impl.TicketPaymentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/ticket/")
public class TicketPaymentController {

    @Autowired
    TicketPaymentServiceImpl ticketPaymentService;

    @PostMapping(value = "/request")
    public ResponseEntity<Object> reserveTickets(final @RequestBody @Valid Request request)  throws TicketBookingException {
        return new ResponseEntity<>(ticketPaymentService.processRequest(request) ,CREATED);
    }


}
