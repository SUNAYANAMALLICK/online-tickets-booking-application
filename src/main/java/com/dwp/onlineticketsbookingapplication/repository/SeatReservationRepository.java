package com.dwp.onlineticketsbookingapplication.repository;

import com.dwp.onlineticketsbookingapplication.entity.SeatReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatReservationRepository extends JpaRepository<SeatReservation, Long> {
}
