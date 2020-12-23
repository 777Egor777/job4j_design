package ru.job4j.lsp.parking;

import java.util.List;

/**
 * Базовый класс для
 * объектов, реализующих
 * интерфейс "Парковка".
 *
 * Будет использоваться в
 * аггрегации с остальными
 * классами.
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 20.12.2020
 */
public final class ParkingBase implements Parking {
    private final PlaceSet set;

    /**
     * Конструктор.
     */
    public ParkingBase(int passengerCarPlaces, int truckPlaces) {
        set = new PlaceSet(passengerCarPlaces, truckPlaces);
    }

    /**
     * Метод паркует машину
     * и возвращает true.
     * <p>
     * Если машину не удалось
     * припарковать,
     * то метод возвращает
     * false.
     *
     * @param car - машина, которую
     *            нужно припарковать
     * @return true - если всё прошло
     * удачно, и машина
     * припаркована.
     * false - иначе.
     */
    @Override
    public final boolean park(Car car) {
        return false;
    }

    /**
     * Машина освобождает
     * парковочные места.
     *
     * @param id - id машины, которая
     *            уезжает с парковки
     *            и освобождает
     *            парковочные места.
     */
    @Override
    public final void remove(int id) {

    }

    /**
     * Метод возвращает список
     * всех находящихся на данный
     * момент машин на парковке.
     *
     * @return список припаркованных
     * машин.
     */
    @Override
    public final List<Car> getCars() {
        return null;
    }

    /**
     * @return Строковое представление
     *         парковки.
     *
     *         Информация по всем
     *         машинам, в том числе о
     *         местах, на которых они
     *         припаркованы.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Класс "парковочное место".
     */
    private final class Place {

        /**
         * Тип парковочного места.
         */
        private final PlaceType type;

        /**
         * Номер парковочного места.
         * (Для каждого типа парковочные
         *  места каждого типа идут
         *  последовательно и так же
         *  пронумерованы. См. PlaceSet.)
         */
        private final int id;

        /**
         * Маркер состояния места.
         *
         * =true - место свободно
         * =false - место занято.
         */
        private boolean empty;


        /**
         * Конструктор.
         * @param type - тип места.
         * @param id - id места в секции
         *             парковки для мест
         *             данного типа.
         */
        private Place(PlaceType type, int id) {
            this.type = type;
            this.id = id;
            empty = true;
        }


        /**
         * Занять это место.
         */
        public final void take() {
        }

        /**
         * Освободить это место.
         */
        public final void free() {
        }

        /**
         * @return true - если место свободно,
         *         false - иначе.
         */
        public final boolean isEmpty() {
            return false;
        }

        /**
         * @return Строковое представление
         *         парковочного места.
         *
         *         Содержит тип и номер
         *         места.
         */
        @Override
        public String toString() {
            return super.toString();
        }
    }

    /**
     * Набор парковочных мест.
     *
     * Вспомогательный класс,
     * который отвечает
     * за занятие/освобождение/
     * проверку наличия мест.
     */
    private final class PlaceSet {

        public PlaceSet(int passengerCarPlaces, int truckPlaces) {
        }


        public boolean canFill(PlaceType type, int numOfPlaces) {
            return true;
        }

        List<Place> fill(PlaceType type, int numOfPlaces) {
            return null;
        }

        public void clear(List<Place> places) {
        }
    }

    private enum PlaceType {
        GENERAL,
        TRUCK
    }
}
