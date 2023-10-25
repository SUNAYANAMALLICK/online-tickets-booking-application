package com.dwp.onlineticketsbookingapplication.service;

import com.dwp.onlineticketsbookingapplication.entity.TicketsPayment;
import com.dwp.onlineticketsbookingapplication.exception.TicketBookingException;
import com.dwp.onlineticketsbookingapplication.model.Request;
import com.dwp.onlineticketsbookingapplication.model.Response;
import org.springframework.http.ResponseEntity;

public interface TicketPaymentService {
    public ResponseEntity<Object> processRequest(final Request request) throws TicketBookingException;

    TicketsPayment processPayment(Long userAccountId, long amount);
}
