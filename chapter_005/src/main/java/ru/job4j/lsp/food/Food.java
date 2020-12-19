package ru.job4j.lsp.food;

import java.util.Calendar;

/**
 * Base class for all
 * products.
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 19.12.2020
 */
public final class Food {
    /**
     * Name of the product.
     */
    private final String name;

    /**
     * Create date.
     */
    private final Calendar create;

    /**
     * Expire date.
     */
    private final Calendar expire;

    /**
     * Price of the product.
     */
    private double price;

    /**
     * Possible discount
     * of the product.
     */
    private double discount;

    /**
     * Constructor.
     *
     * @param name - name.
     * @param create - create date.
     * @param expire - expire date.
     * @param price - price.
     * @param discount - possible discount.
     */
    public Food(String name, Calendar create, Calendar expire, double price, double discount) {
        this.name = name;
        this.create = create;
        this.expire = expire;
        this.price = price;
        this.discount = discount;
    }

    /**
     * Getter for create date.
     * @return create date.
     */
    public final Calendar getCreate() {
        return create;
    }

    /**
     * Getter for expire date.
     * @return expire date.
     */
    public final Calendar getExpire() {
        return expire;
    }

    /**
     * Getter for price.
     * @return price.
     */
    public final double getPrice() {
        return price;
    }

    /**
     * Getter for discount.
     * @return discount.
     */
    public final double getDiscount() {
        return discount;
    }

    /**
     * Setter for price.
     * @param price - price of the
     *                product.
     */
    public final void setPrice(double price) {
        this.price = price;
    }

    /**
     * Setter for discount.
     * @param discount - discount of
     *                   the product.
     */
    public final void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public final String toString() {
        return  String.format("Food{%s,%s,%s,%f,%f}",
                name, create.toString(),
                expire.toString(),
                price, discount);
    }
}
