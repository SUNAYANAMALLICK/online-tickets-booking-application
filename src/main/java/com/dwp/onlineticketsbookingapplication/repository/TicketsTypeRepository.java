package com.dwp.onlineticketsbookingapplication.repository;

import com.dwp.onlineticketsbookingapplication.entity.TicketsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketsTypeRepository extends JpaRepository<TicketsType, Long> {
}
