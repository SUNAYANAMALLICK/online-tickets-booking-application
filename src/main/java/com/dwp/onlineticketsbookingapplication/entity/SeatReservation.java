package com.dwp.onlineticketsbookingapplication.entity;

import com.dwp.onlineticketsbookingapplication.constants.Constants;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = Constants.TBL_SEAT_RESERVATION)
@Data
public class SeatReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "allocated_seat")
    private List<String> allocatedSeat;

}
