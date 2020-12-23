package ru.job4j.lsp.parking;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 23.12.2020
 */
public class Truck implements Car {

    public Truck(String name, String sign, int size) {
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
