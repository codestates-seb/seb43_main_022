//package com.codea.auth.email;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import java.io.UnsupportedEncodingException;
//import java.util.Random;
//
//import static org.springframework.security.core.context.SecurityContextHolder.setContext;
//
//@Service
//@RequiredArgsConstructor
//public class EmailService {
//
//    private final JavaMailSender emailSender;
//    //    private final SpringTemplateEngine templateEngine;
//    private String authNum;
//
//
//    public void createCode() {
//        Random random = new Random();
//        StringBuffer key = new StringBuffer();
//
//        for (int i = 0; i < 4; i++) {
//            int index = random.nextInt(3);
//
//            switch (index) {
//                case 0:
//                    key.append((char) ((int) random.nextInt(26) + 97));
//                    break;
//                case 1:
//                    key.append((char) ((int) random.nextInt(26) + 65));
//                    break;
//                case 2:
//                    key.append(random.nextInt(9));
//                    break;
//            }
//        }
//        authNum = key.toString();
//
//    }
//
//    public MimeMessage createEmailForm(String email) throws MessagingException, UnsupportedEncodingException {
//
//        createCode(); //인증 코드 생성
//        String setFrom = "rnqhstlr2297@naver.com"; //email-config에 설정한 자신의 이메일 주소(보내는 사람)
//        String toEmail = email; //받는 사람
//        String title = "회원가입 인증 번호"; //제목
//
//        MimeMessage message = emailSender.createMimeMessage();
//        message.addRecipients(MimeMessage.RecipientType.TO, email); //보낼 이메일 설정
//        message.setSubject(title); //제목 설정
//        message.setFrom(setFrom); //보내는 이메일
//        message.setText(setContext(authNum), "utf-8", "html");
//
//        return message;
//    }
//
//    public String sendEmail(String toEmail) throws MessagingException, UnsupportedEncodingException {
//
//        //메일전송에 필요한 정보 설정
//        MimeMessage emailForm = createEmailForm(toEmail);
//        //실제 메일 전송
//        emailSender.send(emailForm);
//
//        return authNum; //인증 코드 반환
//    }
//
//}
