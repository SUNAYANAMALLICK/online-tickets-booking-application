package com.dwp.onlineticketsbookingapplication.repository;

import com.dwp.onlineticketsbookingapplication.entity.TicketsPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketPaymentRepository extends JpaRepository<TicketsPayment,Long> {
}
