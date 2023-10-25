package com.dwp.onlineticketsbookingapplication.errorhandle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorModel {
    private String errMessage;
    private HttpStatus errorCode;

}
