package com.dwp.onlineticketsbookingapplication.entity;

import com.dwp.onlineticketsbookingapplication.constants.Constants;
import com.dwp.onlineticketsbookingapplication.enums.TicketTypesEnum;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = Constants.TBL_TICKETS_TYPE)
@Data
public class TicketsType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long id;

    @Column(name = "type")
    private TicketTypesEnum type;

    @Column(name = "price")
    private Double price;

}
