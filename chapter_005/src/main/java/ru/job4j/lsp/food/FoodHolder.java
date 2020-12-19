package ru.job4j.lsp.food;

import java.util.List;

/**
 * Interface for all
 * food-holders
 * (Shop, warehouse, trash etc).
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 19.12.2020
 */
public interface FoodHolder {
    /**
     * Add product to food
     * holder.
     * @param food - product.
     */
    void add(Food food);

    /**
     * Get all product from
     * food holder.
     * @return - all products.
     */
    List<Food> getAll();
}
