package com.codea.exception;

import lombok.Getter;

public enum ExceptionCode {
    DUPLICATED_EMAIL(409, "이메일을 찾을수 없습니다."),
    MEMBER_EXISTS(409, "이메일이 이미 존재"),
    MEMBER_NOT_FOUND(404, "멤버를 찾을수 없습니다."),
    INVALID_PERMISSION(403, "권한이 유효하지 않습니다."),
    REVIEW_NOT_FOUND(404, "Review not found"),
    RESTAURANT_NOT_FOUND(404, "Restaurant not found"),
    RESTAURANT_EXISTS (409, "Restaurant exists");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}