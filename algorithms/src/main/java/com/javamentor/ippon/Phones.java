package com.javamentor.ippon;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Person of Interest is a TV series in which Harold Finch, a multi-billionaire developer, has developed an artificial intelligence called "The Machine", capable of predicting crimes.
 * For each 30-day period, "The Machine" should output the following data list:
 *
 * ﻿Crime predicted: 555-55-5555
 * Calling: 1-555-555-5555
 *
 * Each day is separated by an empty line.
 * The first number is an American social security number, of the form 342-98-1613, each number being random (respectively with 3 digits, 2 digits, 4 digits).
 *
 * The second number is an American phone number, in the form 1-814-555-3857, with:
 *
 *     The first digit is always equal to 1 (country code)
 *     The next number with 3 digits is random
 *     The next number is always equal to 555
 *     Finally the last number with 4 digits is random
 *
 * For each month, a person can only commit one crime: the social security and phone numbers must be unique.
 */
public class Phones {

    public String predictCrimes() {
        StringBuilder str = new StringBuilder().append("Crime predicted: ")
            .append(rnd(3)).append("-")
            .append(rnd(2)).append("-")
            .append(rnd(4)).append(System.lineSeparator())
            .append("Calling: 1-")
            .append(rnd(3)).append("-555-")
            .append(rnd(4)).append(System.lineSeparator());
//        System.out.println(str.length());
        return str.toString();
    }

    private String rnd(int n) {
        String result = "";
        for (int i = 0; i < n; i++) {
            result += ThreadLocalRandom.current().nextInt(0, 9);
        }
        return result;
    }

    public static void main(String[] args) {
        Phones phones = new Phones();
        System.out.println(phones.predictCrimes());
    }
}
