package ru.job4j.lsp.food;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Engine for all food-holder
 * classes. We will use
 * objects of this engine
 * in aggregation with all
 * food-holder objects.
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 19.12.2020
 */
public final class FoodHolderEngine implements FoodHolder {
    /**
     * Name of the food-holder.
     */
    private final String name;

    /**
     * List of all products
     * in the food-holder.
     */
    private final List<Food> products = new ArrayList<>();

    /**
     * Constructor.
     * @param name - name of the
     *               food-holder.
     */
    public FoodHolderEngine(String name) {
        this.name = name;
    }

    /**
     * Add product to food
     * holder.
     *
     * @param food - product.
     */
    @Override
    public final void add(Food food) {
        products.add(food);
    }

    /**
     * Get all product from
     * food holder.
     *
     * @return - all products.
     */
    @Override
    public final List<Food> getAll() {
        return products;
    }

    @Override
    public final String toString() {
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        joiner.add(name);
        joiner.add("Number of products: " + products.size());
        products.forEach(pr -> joiner.add(pr.toString()));
        return joiner.toString();
    }
}
