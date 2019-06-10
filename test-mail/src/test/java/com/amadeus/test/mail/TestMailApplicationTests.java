package com.amadeus.test.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMailApplicationTests {

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    public void sendSimpleMail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("804236345@qq.com");
        simpleMailMessage.setTo("200572387@qq.com");
        simpleMailMessage.setSubject("主题：验证码");
        int verificationCode= (int) ((Math.random()*9+1)*100000);
        System.out.println(verificationCode);
        simpleMailMessage.setText(String.valueOf(verificationCode));
        javaMailSender.send(simpleMailMessage);
    }

}
