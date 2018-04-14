package ru.job4j.calculator;

/**
 * Basic calculator class.
 * @author Shonorov.
 */
public class Calculator {
    /**
     * Result of actions.
     */
    Double result;
    /**
     * Summation of two values.
     * @param first value.
     * @param second value.
     */
    public void add(double first, double second) {
        this.result = first + second;
    }
    /**
     * Substraction of two values
     * @param first value.
     * @param second value.
     */
    public void substract(double first, double second) {
        this.result = first - second;
    }
    /**
     * Division of two values
     * @param first value.
     * @param second value.
     */
    public void div(double first, double second) {
        this.result = first / second;
    }
    /**
     * Multiplication of two values.
     * @param first value.
     * @param second value.
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }
    /**
     * Getter for result.
     * @return result.
     */
    public double getResult() {
        return this.result;
    }
}
