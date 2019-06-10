package com.amadeus.framework.utils;

import java.util.UUID;

public class UUIDUtil {
    public String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.println(new UUIDUtil().generateUUID());
    }
}
