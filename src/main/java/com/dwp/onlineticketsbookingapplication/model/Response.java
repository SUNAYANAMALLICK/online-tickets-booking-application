package com.dwp.onlineticketsbookingapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Response {
    private Long requestId;
    private List<String> allocatedSeat;
    private Long totalCost;

}
