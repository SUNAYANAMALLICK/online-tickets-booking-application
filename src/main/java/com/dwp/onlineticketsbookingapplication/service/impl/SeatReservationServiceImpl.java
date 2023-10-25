package com.dwp.onlineticketsbookingapplication.service.impl;

import com.dwp.onlineticketsbookingapplication.entity.SeatReservation;
import com.dwp.onlineticketsbookingapplication.repository.SeatReservationRepository;
import com.dwp.onlineticketsbookingapplication.service.SeatReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class SeatReservationServiceImpl implements SeatReservationService {

    @Autowired
    SeatReservationRepository seatReservationRepository;
    @Override
    public List<String> generateSeatNumber(final int count, final Long id) {

        List<String> allocatedSeat =  new ArrayList<>();
        IntStream.range(0, count).forEach(value -> allocatedSeat.add(String.format("%s-%s",id,value)));
        saveToDb(id, allocatedSeat);
        return allocatedSeat;
    }

    public void saveToDb(final Long userId, final List<String> allocatedList){

        SeatReservation seatReservation = new SeatReservation();
        seatReservation.setId(userId);
        seatReservation.setAllocatedSeat(allocatedList);
        seatReservationRepository.save(seatReservation);

    }
}
