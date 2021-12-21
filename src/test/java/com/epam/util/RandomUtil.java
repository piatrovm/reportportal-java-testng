package com.epam.util;

import java.util.Random;

public class RandomUtil {

    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String getRandomString(int length) {
        Random r = new Random();
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            randomString.append(
                    ALPHABET.charAt(r.nextInt(ALPHABET.length()))
            );
        }
        return randomString.toString();
    }
}
