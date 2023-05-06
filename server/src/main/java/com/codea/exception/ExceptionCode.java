package com.codea.exception;

import lombok.Getter;

public enum ExceptionCode {
    RESTAURANT_NOT_FOUND(404, "Restaurant not found"),
    RESTAURANT_EXISTS (409, "Restaurant exists");
    @Getter
    private int status;

    @Getter
    private  String message;

    ExceptionCode(int code, String message){
        this.status = code;
        this.message = message;
    }
}
