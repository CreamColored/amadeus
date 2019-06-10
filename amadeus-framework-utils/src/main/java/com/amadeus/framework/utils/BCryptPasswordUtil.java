package com.amadeus.framework.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordUtil{

    public String encodePassword(BCryptPasswordEncoder encoder,String rawPassword) {
        return encoder.encode(rawPassword);
    }

    public boolean matchPassword(BCryptPasswordEncoder encoder,String rawPassword,String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordUtil().matchPassword(new BCryptPasswordEncoder(),"wxp804236345","$2a$10$0N14dKSsgQFYGedlcjpQduLzqA7pkbrzcd2J7zQcbAc5A3h6GLEni"));
    }
}
