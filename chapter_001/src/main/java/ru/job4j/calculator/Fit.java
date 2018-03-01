package ru.job4j.calculator;
/**
 * Программа расчета идеального веса.
 */
public class Fit {
    /**
     * Weight indexes.
     */
    private static final int MEN_INDEX = 100;
    private static final int WOMEN_INDEX = 110;
    private static final double OVERALL_INDEX = 1.15;
    /**
     * Идеальный вес для мужщины.
     * @param height Рост.
     * @return идеальный вес.
     */
    double manWeight(double height) {
        return (height - MEN_INDEX) * OVERALL_INDEX;
    }
    /**
     * Идеальный вес для женщины.
     * @param height Рост.
     * @return идеальный вес.
     */
    double womanWeight(double height) {
        return (height - WOMEN_INDEX) * OVERALL_INDEX;
    }
}
