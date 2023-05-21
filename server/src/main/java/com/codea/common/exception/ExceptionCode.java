package com.codea.common.exception;

import lombok.Getter;

public enum ExceptionCode {
    DUPLICATED_EMAIL(409, "이메일을 찾을 수 없습니다."),
    MEMBER_EXISTS(409, "이메일이 이미 존재"),
    MEMBER_NOT_FOUND(404, "멤버를 찾을 수 없습니다."),
    INVALID_PERMISSION(403, "권한이 유효하지 않습니다."),
    REVIEW_NOT_FOUND(404, "리뷰를 찾을 수 없습니다."),
    RESTAURANT_NOT_FOUND(404, "맛집을 찾을 수 없습니다."),
    MENU_NOT_FOUND(409, "메뉴를 찾을 수 없습니다."),
    ADDRESS_NOT_FOUND(409, "주소를 찾을 수 없습니다."),
    LOCATION_NOT_FOUND(409, "좌표를 찾을 수 없습니다."),
    UNAUTHORIZED_EDIT(401,"권한이 없습니다."),
    CATEGORY_EXISTS(409,"Category exists"),
    CATEGORY_NOT_FOUND(404, "Category not found" ),
    TAG_EXISTS(409,"Tag exists"),
    TAG_NOT_FOUND(404,"Tag not found"),
    FAVORITE_NOT_FOUND(404, "즐겨찾기한 맛집을 찾을 수 없습니다.");


    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
