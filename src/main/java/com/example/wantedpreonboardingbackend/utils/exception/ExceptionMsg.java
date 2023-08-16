package com.example.wantedpreonboardingbackend.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExceptionMsg {
    private int errorNum;
    private String errorMsg;
}
