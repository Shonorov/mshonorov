package ru.job4j.map;

import java.util.HashMap;

/**
 * Check if two words consist of the same letters.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SameLetters {

    /**
     * Compare two words by letters.
     * @param word1 to compare.
     * @param word2 to compare.
     * @return true if same letters.
     */
    public boolean compare(String word1, String word2) {
        boolean result = false;
        if (fill(word1).equals(fill(word2))) {
            result = true;
        }
        return result;
    }

    /**
     * Get map of character with its counts.
     * @param word word to check.
     * @return map of character counts.
     */
    private HashMap<Character, Integer> fill(String word) {
        HashMap<Character, Integer> letters = new HashMap<>();
        for (Character current : word.toCharArray()) {
            Character character = Character.toLowerCase(current);
            if (!letters.containsKey(character)) {
                letters.put(character, 1);
            } else {
                letters.replace(character, letters.get(character) + 1);
            }
        }
        return letters;
    }
}
