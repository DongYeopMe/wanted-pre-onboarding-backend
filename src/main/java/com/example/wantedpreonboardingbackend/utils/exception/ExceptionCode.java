package com.example.wantedpreonboardingbackend.utils.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    MEMBER_EMAIL_EXISTS(409, "Member email exists"),
    MEMBER_NAME_EXISTS(409, "Member name exists"),
    POST_NOT_FOUND(404, "Post not Found"),
    Member_NOT_FOUND(404, "Member not Found"),
    MEMBER_NOT_MATCH(404, "Member not Match");
    private int status;

    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
