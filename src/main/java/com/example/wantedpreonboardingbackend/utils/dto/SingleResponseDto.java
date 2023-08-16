package com.example.wantedpreonboardingbackend.utils.dto;

import com.example.wantedpreonboardingbackend.utils.exception.ExceptionMsg;
import lombok.Getter;

@Getter
public class SingleResponseDto<T> {

    private T data;
    private ExceptionMsg exceptionMsg = null;

    public SingleResponseDto(T data) {
        this.data = data;
    }

    public SingleResponseDto(T data, ExceptionMsg exceptionMsg) {
        this.data = data;
        this.exceptionMsg = exceptionMsg;
    }

    public SingleResponseDto(ExceptionMsg exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }
}
