package com.amadeus.framework.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class VerificationCodeUtil {

    public int generateVerificationCode() {
        return (int) ((Math.random() * 9 + 1) * 100000);
    }

    public int sendEmailVerificationCode(JavaMailSender sender, String email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("804236345@qq.com");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("主题：验证码");
        int verificationCode = new VerificationCodeUtil().generateVerificationCode();
        simpleMailMessage.setText(String.valueOf(verificationCode));
        sender.send(simpleMailMessage);
        return verificationCode;
    }

    public void sendEmailAndPassword(SimpleMailMessage simpleMailMessage,JavaMailSender sender) {
        sender.send(simpleMailMessage);
    }
}
