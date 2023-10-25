package com.dwp.onlineticketsbookingapplication.repository;

import com.dwp.onlineticketsbookingapplication.entity.UserAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserAccountsRepository extends JpaRepository<UserAccounts, Long> {

    public UserAccounts findByEmailId(final String emailId);
}
