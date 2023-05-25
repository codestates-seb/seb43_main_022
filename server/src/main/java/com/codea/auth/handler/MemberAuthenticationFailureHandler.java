package com.codea.auth.handler;

import com.codea.common.response.ErrorResponse;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MemberAuthenticationFailureHandler implements AuthenticationFailureHandler {
    // 인증 실패시 처리 구현
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {

        log.error("# Authentication failed: {}", exception.getMessage());

        sendErrorResponse(response);
        // 클라이언트에게 오류 응답
    }

    public void sendErrorResponse(HttpServletResponse response) throws IOException {
        // 클라이언트에게 전송할 오류 응답 생성 및 전송
        Gson gson = new Gson();
        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); // 응답 컨텐츠 타입을 application/json 으로 설정
        response.setStatus(HttpStatus.UNAUTHORIZED.value()); // 응답 상태 코드를 HttpStatus.UNAUTHORIZED로 설정
        response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class));
        // json 형식으로 변환한 문자열을 response에 작성하여 클라이언트에게 전송
    }
}