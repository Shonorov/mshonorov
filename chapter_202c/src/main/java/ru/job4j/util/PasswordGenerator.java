package ru.job4j.util;

import java.security.SecureRandom;

/**
 * Generate password for new user.
 */
public class PasswordGenerator {

    private static SecureRandom random = new SecureRandom();

    private static final String CAPITALS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SET = CAPITALS + CHARS + NUMBERS;

    public static String generatePassword(int len) {
        String result = "";
        for (int i = 0; i < len; i++) {
            int index = random.nextInt(SET.length());
            result += SET.charAt(index);
        }
        return result;
    }
}
