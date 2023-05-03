package com.codea.exception;

import lombok.Getter;

public enum ExceptionCode {
    REVIEW_NOT_FOUND(404, "Review not found");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
