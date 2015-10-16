package com.github.alexey_pushkin.java_calculator;

public class Calculator {

    private Double valueA;
    private Double valueB;
    private Operation operation;
    private Double result;
    private Boolean isATurn = true;

    public enum Operation { ADD, SUBTRACT, MULTIPLY, DIVIDE, SQRT }

    public void addValue(double input) {
        if (isATurn) {
            valueA = input;
        } else {
            valueB = input;
        }
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
        isATurn = false;
    }

    public Double getResult() {

        if (valueA == null && valueB == null) {
            return null;
        } else if (valueA == null) {
            valueA = 0.0;
        } else if (valueB == null) {
            valueB = 0.0;
        }

        switch (operation) {
            case ADD: result = valueA + valueB; break;
            case SUBTRACT: result = valueA - valueB; break;
            case MULTIPLY: result = valueA * valueB; break;
            case DIVIDE: result = valueA / valueB; break;
            case SQRT: result = Math.sqrt(valueA); break;
            default: result = 0.0;
        }

        valueA = result;
        isATurn = true;

        return result;
    }

    public void clearEntry() {
        if (isATurn) {
            valueA = null;
        } else {
            valueB = null;
        }
    }

    public void clearAll() {
        valueA = null;
        valueB = null;
        operation = null;
        isATurn = true;
    }
}
