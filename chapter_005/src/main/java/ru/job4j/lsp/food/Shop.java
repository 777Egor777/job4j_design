package ru.job4j.lsp.food;

import ru.job4j.lsp.food.util.Math;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 19.12.2020
 */
public final class Shop implements Storage {
    private final static String NAME = "Shop";
    private final static Math MATH = new Math();
    private final static double LEFT_THRESHOLD_ADD = 0.25;
    private final static double RIGHT_THRESHOLD_ADD = 1.0;
    private final static Predicate<Food> CAN_ADD_PRED = food -> {
        double ratio = MATH.ratio(food);
        return MATH.moreOrEquals(ratio, LEFT_THRESHOLD_ADD)
                    && MATH.less(ratio, RIGHT_THRESHOLD_ADD);

    };
    private final static Discounter DISCOUNTER = new Discounter();
    private final static double THRESHOLD_DISCOUNT = 0.75;
    private final static Predicate<Food> CAN_DISCOUNT = food -> {
        double ratio = MATH.ratio(food);
        return MATH.more(ratio, THRESHOLD_DISCOUNT);
    };

    private final Storage engine = new StorageEngine(NAME, CAN_ADD_PRED);

    /**
     * Add product to food
     * holder.
     *
     * @param food - product.
     */
    @Override
    public final void add(Food food) {
        engine.add(food);
        if (CAN_DISCOUNT.test(food)) {
            DISCOUNTER.apply(food);
        }
    }

    @Override
    public final boolean accept(Food food) {
        return engine.accept(food);
    }

    /**
     * Get all product from
     * food holder.
     *
     * @return - all products.
     */
    @Override
    public final List<Food> clear() {
        return engine.clear();
    }

    @Override
    public final String toString() {
        return engine.toString();
    }
}
