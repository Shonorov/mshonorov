package com.javamentor.strategy;

/**
 * Save jpeg file strategy.
 */
public class SaveFileJPEG implements SaveFileStrategy {

    @Override
    public String save(String filename) {
        System.out.println("File " + filename + ".jpeg saved!");
        return filename + ".jpeg";
    }
}
