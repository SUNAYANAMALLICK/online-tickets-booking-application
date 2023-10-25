package com.dwp.onlineticketsbookingapplication.service;

import com.dwp.onlineticketsbookingapplication.entity.UserAccounts;
import com.dwp.onlineticketsbookingapplication.exception.TicketBookingException;
import com.dwp.onlineticketsbookingapplication.repository.UserAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserAccountsService {

    @Autowired
    UserAccountsRepository userAccountsRepository;

    public UserAccounts addOrUpdateUserAccounts(final UserAccounts userAccounts){
        return userAccountsRepository.save(userAccounts);
    }

    public UserAccounts findUserAccountsByEmailId(final String emailId){
        return userAccountsRepository.findByEmailId(emailId);
    }

    public UserAccounts checkForValidUser(final String emailId) throws TicketBookingException {
        // check for valid user - account id is greater than zero
        UserAccounts userAccounts = findUserAccountsByEmailId(emailId);
        if(null==userAccounts){
            userAccounts = createNewUserAccountsWithEmailId(emailId);
        }
        // Check for valid account id
        if(userAccounts.getId()==0 || null==userAccounts.getId())
        {
            throw new TicketBookingException("Invalid User Account.");
        }
        return userAccounts;
    }

    private UserAccounts createNewUserAccountsWithEmailId(final String emailId) {
        UserAccounts userAccounts = new UserAccounts();
        userAccounts.setEmailId(emailId);
        userAccounts.setTotalCredit(0.00);
        userAccounts.setRequestIds(new ArrayList<>());
        return addOrUpdateUserAccounts(userAccounts);
    }
}
