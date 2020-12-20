package ru.job4j.lsp.food.util;

import ru.job4j.lsp.food.Food;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 20.12.2020
 */
public final class Math {
    private final static double PRECISION = 0.0000001;

    public final double ratio(long first, long second) {
        return (double) first / (double) second;
    }

    public final double ratio(Food food) {
        double result = 1.01;
        if (food.getAllTimeInMillis() != 0) {
            result = ratio(food.getLiveTimeInMillis(),
                           food.getAllTimeInMillis());
        }
        return result;
    }

    public final boolean more(double first, double second) {
        return first - second > PRECISION;
    }

    public final boolean moreOrEquals(double first, double second) {
        return first - second > -PRECISION;
    }

    public final boolean equals(double first, double second) {
        return java.lang.Math.abs(first - second) < PRECISION;
    }

    public final boolean lessOrEquals(double first, double second) {
        return first - second < PRECISION;
    }

    public final boolean less(double first, double second) {
        return first - second < -PRECISION;
    }
}
