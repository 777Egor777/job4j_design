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

    /**
     * Конструктор принимает
     * перечисление, образующее
     * массив количеств мест,
     * которая занимает данная
     * машина для каждого типа
     * мест.
     *
     * @param numOfPlacesOfEachType
     * @throws IllegalArgumentException - если количество аргументов
     *                                    не равно числу элементов
     *                                    е-нумератора PlaceType,
     *                                    пробрасывается исключение
     *                                    с сообщением о неверном
     *                                    числе аргументов.
     */
    public CarBase(int... numOfPlacesOfEachType) throws IllegalArgumentException {
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

    /**
     * @param type - тип парковочного места,
     *             задаваемый е-нумератором
     *             PlaceType.
     * @return количество мест данного типа,
     *         которое занимает данная машина.
     */
    @Override
    public final int getCountOfPlaces(PlaceType type) {
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
