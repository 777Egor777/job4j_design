package ru.job4j.lsp.parking;

import java.util.*;
import java.util.stream.Collectors;

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
    private final List<Car> cars = new ArrayList<>();
    private final Map<Car, List<Place>> placesOfCar = new HashMap<>();

    /**
     * Конструктор.
     * Принимает перечисление
     * количеств парковочных мест
     * каждого существующего на
     * данный момент типа мест
     * на парковке.
     *
     * В этой версии программы
     * всего два типа мест -
     * для легковых и для
     * грузовых.
     *
     * Но при добавлении доп. типов
     * мест, код изменять будет
     * не нужно.
     *
     * Итак, на данный момент конструктор
     * должен принимать 2 аргумента.
     *
     * Первый аргумент - количество
     * парковочных мест для легковых
     * машин.
     *
     * Второй аргумент - количество
     * парковочных мест для грузовых
     * машин.
     *
     * Если аргументов будет меньше
     * двух, конструктор пробросит
     * IllegalArgumentException.
     *
     * @param numOfEachTypePlaces - количества парковочных
     *                              мест каждого типа.
     * @throws IllegalArgumentException - исключение, которое будет
     *                                    выброшено в том случае,
     *                                    если аргументов недостаточно
     *                                    (объект создаётся без
     *                                    необходимых данных по кол-ву
     *                                    парковочных мест).
     */
    public ParkingBase(int... numOfEachTypePlaces) throws IllegalArgumentException {
        if (numOfEachTypePlaces.length < PlaceType.values().length) {
            throw new IllegalArgumentException();
        }
        set = new PlaceSet(numOfEachTypePlaces);
    }

    private Map<PlaceType, Integer> numOfPlacesForCar(Car car) {
        Map<PlaceType, Integer> map = new HashMap<>();
        if (car.getSize() > 1) {
            map.put(PlaceType.TRUCK, 1);
        } else {
            map.put(PlaceType.TRUCK, (int) 1e9);
        }
        map.put(PlaceType.GENERAL, car.getSize());
        return map;
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
        try {
            boolean result = false;
            Map<PlaceType, Integer> numOfPlaces = numOfPlacesForCar(car);
            for (PlaceType type : PlaceType.values()) {
                int startPos = set.canFill(type, numOfPlaces.get(type));
                if (startPos >= 0) {
                    List<Place> places = set.fill(type, startPos, numOfPlaces.get(type));
                    cars.add(car);
                    placesOfCar.put(car, places);
                    result = true;
                    break;
                }
            }
            return result;
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private Car findCarById(int id) {
        Car result = null;
        for (Car car : cars) {
            if (car.getId() == id) {
                result = car;
                break;
            }
        }
        return result;
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
        Car car = findCarById(id);
        cars.remove(car);
        set.clear(placesOfCar.get(car));
        placesOfCar.remove(car);
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
        return cars;
    }

    private String carToStr(Car car) {
        StringJoiner joiner = new StringJoiner(", ", " ", ";");
        for (Place place: placesOfCar.get(car)) {
            joiner.add(place.toString());
        }
        return car.toString() + joiner.toString();
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
        return cars.stream().map(this::carToStr).collect(Collectors.joining(System.lineSeparator()));
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
            empty = false;
        }

        /**
         * Освободить это место.
         */
        public final void free() {
            empty = true;
        }

        /**
         * @return true - если место свободно,
         *         false - иначе.
         */
        public final boolean isEmpty() {
            return empty;
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
            return type.toString() + id;
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
        private Map<PlaceType, Integer> numOfPlaces = new HashMap<>();
        private Map<PlaceType, List<Place>> places = new HashMap<>();

        public PlaceSet(int... numOfEachTypePlaces) {
            for (int i = 0; i < PlaceType.values().length; ++i) {
                numOfPlaces.put(PlaceType.values()[i], numOfEachTypePlaces[i]);
            }
            init();
        }

        private void init() {
            for (int i = 0; i < PlaceType.values().length; ++i) {
                PlaceType type = PlaceType.values()[i];
                places.put(type, new ArrayList<>());
                for (int j = 0; j < numOfPlaces.get(type); ++j) {
                    places.get(type).add(new Place(type, j));
                }
            }
        }

        public int canFill(PlaceType type, int numOfPlaces) {
            int result = -1;
            List<Place> typePlaces = places.get(type);
            int sz = typePlaces.size();
            int currentFreeRow = 0;
            int startOfRow = -1;
            for (int i = 0; i < sz; ++i) {
                Place place = typePlaces.get(i);
                if (place.isEmpty()) {
                    if (currentFreeRow == 0) {
                        startOfRow = i;
                        currentFreeRow = 1;
                    } else {
                        currentFreeRow++;
                    }
                    if (currentFreeRow >= numOfPlaces) {
                        result = startOfRow;
                        break;
                    }
                } else {
                    currentFreeRow = 0;
                    startOfRow = -1;
                }
            }
            return result;
        }

        List<Place> fill(PlaceType type, int startPos, int numOfPlaces) {
            List<Place> result = places.get(type).subList(startPos, startPos + numOfPlaces);
            for (Place place : result) {
                place.take();
            }
            return result;
        }

        public void clear(List<Place> places) {
            for (Place place : places) {
                place.free();
            }
        }
    }

    private enum PlaceType {
        GENERAL,
        TRUCK
    }
}
