package ru.job4j.lsp.food;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;

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
public final class StorageEngine implements Storage {
    /**
     * Name of the food-holder.
     */
    private final String name;

    /**
     * List of all products
     * in the food-holder.
     */
    private final List<Food> products = new ArrayList<>();

    private final Predicate<Food> addPred;

    /**
     * Constructor.
     * @param name - name of the
     *               food-holder.
     */
    public StorageEngine(String name, Predicate<Food> pred) {
        this.name = name;
        addPred = pred;
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

    @Override
    public final boolean accept(Food food) {
        return addPred.test(food);
    }

    /**
     * Get all product from
     * food holder.
     *
     * @return - all products.
     */
    @Override
    public final List<Food> clear() {
        List<Food> result = new ArrayList<>(products);
        products.clear();
        return result;
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
