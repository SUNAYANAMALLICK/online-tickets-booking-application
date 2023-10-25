package com.dwp.onlineticketsbookingapplication.model;

import lombok.*;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Request {
    private String emailId;
    private Integer adultCount;
    private Integer childCount;
    private Integer infantCount;
}
