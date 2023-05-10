package com.codea.auth.handler;

import com.codea.auth.utils.ErrorResponder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class MemberAccessDeniedHandler implements AccessDeniedHandler {
    // 접근 권한이 없는 경우 접근 불가
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ErrorResponder.sendErrorResponse(response, HttpStatus.FORBIDDEN); // 클라이언트에게 403 Forbidden 상태 코드와 함께 오류 응답 전송
        log.warn("Forbidden error happened: {}", accessDeniedException.getMessage()); // 로거를 사용하여 워닝 로그를 기록, 예외 메세지를 로그에 포함 시킴
    }
}
