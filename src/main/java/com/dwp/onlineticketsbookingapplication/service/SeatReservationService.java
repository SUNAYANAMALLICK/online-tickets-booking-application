package com.dwp.onlineticketsbookingapplication.service;

import java.util.List;

public interface SeatReservationService {

    public List<String> generateSeatNumber(final int count, final Long id);

}
