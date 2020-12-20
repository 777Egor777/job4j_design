package ru.job4j.lsp.parking;

import java.util.List;

/**
 * Базовый интерфейс для
 * объектов типа "машина".
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 20.12.2020
 */
public interface Car {

    /**
     * При создании каждого
     * объекта машины, ему
     * задаётся порядковый
     * уникальный
     * идентификатор.
     *
     * @return id машины.
     */
    int getId();

    /**
     * @param type - тип парковочного места,
     *             задаваемый е-нумератором
     *             PlaceType.
     * @return количество мест данного типа,
     *         которое занимает данная машина.
     */
    int getCountOfPlaces(PlaceType type);
}
