package ru.job4j.lsp.food;

import java.util.List;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 19.12.2020
 */
public final class Shop implements FoodHolder {
    private final static String NAME = "Shop";
    private final FoodHolder engine = new FoodHolderEngine(NAME);

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
}
