package com.javamentor.ippon;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Gary and Dave want to play Dungeons & Dragons.
 * They play with:
 *
 *     A number D of dice
 *     Each die having F faces, knowing that F >= 6
 *
 * What is the probability that at least one of the dice will make a 6?
 * This probability will be approximated to the nearest 5th decimal place.
 */
public class DandD {

    public BigDecimal throwDices(int D, int F) {
        BigDecimal chance = BigDecimal.ONE;
        if (F >= 6) {
            Double result = (Math.pow(F, D) - Math.pow(F - 1, D)) / Math.pow(F, D);
            chance = new BigDecimal(result);
        }
        return chance.setScale(5, RoundingMode.HALF_EVEN);
    }

    public static void main(String[] args) {
        DandD dnd = new DandD();
        //0.30556
        System.out.println(dnd.throwDices(2, 6));
        //0.097500
        System.out.println(dnd.throwDices(2, 20));
        //0.83849
        System.out.println(dnd.throwDices(10, 6));
    }
}
