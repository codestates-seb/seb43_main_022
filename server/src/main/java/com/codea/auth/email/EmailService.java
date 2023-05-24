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
//        // 코드를 생성합니다.
//        createCode();
//        String setFrom = "subgusals6650@gmail.com";    // 보내는 사람
//        String toEmail = email;        // 받는 사람(값 받아옵니다.)
//        String title = "인증번호 테스트";        // 메일 제목
//
//        MimeMessage message = emailSender.createMimeMessage();
//
//        message.addRecipients(MimeMessage.RecipientType.TO, toEmail);    // 받는 사람 설정
//        message.setSubject(title);        // 제목 설정
//
//        // 메일 내용 설정
//        String msgOfEmail = "";
//        msgOfEmail += "<div style='margin:20px;'>";
//        msgOfEmail += "<h1> 안녕하세요 test 입니다. </h1>";
//        msgOfEmail += "<br>";
//        msgOfEmail += "<p>아래 코드를 입력해주세요<p>";
//        msgOfEmail += "<br>";
//        msgOfEmail += "<p>감사합니다.<p>";
//        msgOfEmail += "<br>";
//        msgOfEmail += "<div align='center' style='border:1px solid black; font-family:verdana';>";
//        msgOfEmail += "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
//        msgOfEmail += "<div style='font-size:130%'>";
//        msgOfEmail += "CODE : <strong>";
//        msgOfEmail += authNum + "</strong><div><br/> ";
//        msgOfEmail += "</div>";
//
//        message.setFrom(setFrom);        // 보내는 사람 설정
//        // 위 String으로 받은 내용을 아래에 넣어 내용을 설정합니다.
//        message.setText(msgOfEmail, "utf-8", "html");
//
//        return message;
//    }
//
//    //실제 메일 전송
//    public String sendEmail(String email) throws MessagingException, UnsupportedEncodingException {
//
//        //메일전송에 필요한 정보 설정
//        MimeMessage emailForm = createEmailForm(email);
//        //실제 메일 전송
//        emailSender.send(emailForm);
//
//        return authNum; //인증 코드 반환
//    }
//}
