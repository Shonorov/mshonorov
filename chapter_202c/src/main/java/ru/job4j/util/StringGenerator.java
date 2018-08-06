package ru.job4j.util;

import java.security.SecureRandom;

/**
 * Generate password for new user.
 */
public class StringGenerator {

    private static SecureRandom random = new SecureRandom();

    private static final String CAPITALS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SET = CAPITALS + CHARS;

    public static String generateSting(int len, boolean containsNumbers) {
        String result = "";
        String keySet = SET;
        if (containsNumbers) {
            keySet += NUMBERS;
        }
        for (int i = 0; i < len; i++) {
            int index = random.nextInt(keySet.length());
            result += keySet.charAt(index);
        }
        return result;
    }

}
