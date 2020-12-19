package ru.job4j.lsp.food;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 19.12.2020
 */
public final class Discounter {
    public final void apply(Food food) {
        double newPrice = food.getPrice() * (1.0 - food.getDiscount() / 100.0);
        food.setPrice(newPrice);
        food.setDiscount(0);
    }
}
