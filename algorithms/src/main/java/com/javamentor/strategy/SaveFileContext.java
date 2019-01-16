package com.javamentor.strategy;

/**
 * Saves file as of context type.
 */
public class SaveFileContext {

    private SaveFileStrategy strategy;

    public void setStrategy(SaveFileStrategy strategy) {
        this.strategy = strategy;
    }

    public String saveFile(String filename) {
        return strategy.save(filename);
    }
}
