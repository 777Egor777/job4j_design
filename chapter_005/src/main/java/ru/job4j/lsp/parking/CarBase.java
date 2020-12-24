package ru.job4j.lsp.parking;

import java.util.List;

/**
 * Базовый класс для объектов,
 * реализующих интерфейс Car.
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 20.12.2020
 */
public final class CarBase implements Car {
    private static int nextId = 1;
    private final String name;
    private final String sign;
    private final int size;
    private final int id = nextId++;

    public CarBase(String name, String sign, int size) {
        this.name = name;
        this.sign = sign;
        this.size = size;
    }

    /**
     * При создании каждого
     * объекта машины, ему
     * задаётся порядковый
     * уникальный
     * идентификатор.
     *
     * @return id машины.
     */
    @Override
    public final int getId() {
        return id;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Car)) {
            return false;
        }
        Car car = (Car) obj;
        return id == car.getId();
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("{id=%d, name=%s, sign=%s}", id, name, sign);
    }
}
