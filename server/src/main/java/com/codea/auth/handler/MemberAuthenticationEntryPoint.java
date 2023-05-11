package com.codea.auth.handler;

import com.codea.auth.utils.ErrorResponder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class MemberAuthenticationEntryPoint implements AuthenticationEntryPoint {
    // 사용자 인증이 실패했을때 처리하는 클래스
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        Exception exception = (Exception) request.getAttribute("exception");
        // HttpServletRequest 객체의 속성에서 exception 속성의 값을 가져옴

        ErrorResponder.sendErrorResponse(response, HttpStatus.UNAUTHORIZED);
        // 클라이언트에게 401 Unauthorized 코드로 응답

        logExceptionMessage(authException, exception);
        // 로거를 사용하여 워닝 로그를 기록
    }

    private void logExceptionMessage(AuthenticationException authException, Exception exception) {
        String message = exception != null ? exception.getMessage() : authException.getMessage();
        // exception 객체가 null 이 아닌 경우 해당 예외 메시지를 사용
        // 그렇지 않은경우 authException의 메시지를 사용

        log.warn("Unauthorized error happened: {}", message);
        // 로그 기록
    }
}
