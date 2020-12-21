package ru.job4j.lsp.food;

import ru.job4j.lsp.food.util.Math;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 19.12.2020
 */
public final class Warehouse implements Storage {
    private final static String NAME = "Warehouse";
    private final static Math MATH = new Math();
    private final static double THRESHOLD_ADD = 0.25;
    private final static Predicate<Food> CAN_ADD_PRED = food -> {
        double ratio = MATH.ratio(food);
        return MATH.less(ratio, THRESHOLD_ADD);
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
    public final List<Food> getAll() {
        return engine.getAll();
    }

    @Override
    public final String toString() {
        return engine.toString();
    }

    @Override
    public final void clean() {
        engine.clean();
    }
}
