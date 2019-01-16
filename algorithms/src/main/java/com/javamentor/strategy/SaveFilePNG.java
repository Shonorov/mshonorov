package com.javamentor.strategy;

/**
 * Save png file strategy.
 */
public class SaveFilePNG implements SaveFileStrategy {

    @Override
    public String save(String filename) {
        System.out.println("File " + filename + ".png saved!");
        return filename + ".png";
    }
}
