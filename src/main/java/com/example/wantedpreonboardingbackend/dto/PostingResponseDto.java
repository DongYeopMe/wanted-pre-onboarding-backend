package com.example.wantedpreonboardingbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
@Getter
@AllArgsConstructor
public class PostingResponseDto {
    private String name;

    private String Title;

    private String content;

    private Date created_At;
}
