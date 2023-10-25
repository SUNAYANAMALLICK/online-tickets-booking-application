package com.dwp.onlineticketsbookingapplication.entity;

import com.dwp.onlineticketsbookingapplication.constants.Constants;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Entity
@Table(name = Constants.TBL_USER_ACCOUNTS)
@Data
@Getter
@Setter
public class UserAccounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "payment_id")
    private List<Long> requestIds;

    @Column(name = "total_credit_amount")
    private Double totalCredit;

}
