package com.dwp.onlineticketsbookingapplication.entity;

import com.dwp.onlineticketsbookingapplication.constants.Constants;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = Constants.TBL_TICKETS_PAYMENT)
@Data
public class TicketsPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user")
    private Long user;

    @Column(name="request_id")
    private Long amount;

    @Column(name="status")
    private String status;



}
