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

    public CarBase(String name, String sign, int size) {
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
        return 0;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


}
