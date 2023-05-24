//package com.codea.auth.email;
//
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.mail.MessagingException;
//import javax.validation.constraints.NotEmpty;
//import java.io.UnsupportedEncodingException;
//
//@RestController
//@RequiredArgsConstructor
//public class EmailController {
//
//    private final EmailService emailService;
//
//    @PostMapping("login/mailConfirm")
//    public String mailConfirm(@RequestBody EmailAuthRequestDto emailDto) throws MessagingException, UnsupportedEncodingException {
//
//        String authCode = emailService.sendEmail(emailDto.getEmail());
//        return authCode;
//    }
//
//    @Data
//    public static class EmailAuthRequestDto {
//
//        @NotEmpty(message = "이메일을 입력해주세요")
//        public String email;
//    }
//}