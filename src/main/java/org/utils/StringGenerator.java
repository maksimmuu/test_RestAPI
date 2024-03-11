package org.utils;

import java.util.Random;

public class StringGenerator {
    public static String generate(int lengthString) {
        String acceptableChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < lengthString; i++) {
            int randomIndex = random.nextInt(acceptableChars.length());
            stringBuilder.append(acceptableChars.charAt(randomIndex));
        }

        return stringBuilder.toString();
    }
}
