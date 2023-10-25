package com.dwp.onlineticketsbookingapplication.model;


import com.dwp.onlineticketsbookingapplication.enums.TicketTypesEnum;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TicketsTypeModel {
    private Long typeId;
    private TicketTypesEnum type;
    private Double price;
}
