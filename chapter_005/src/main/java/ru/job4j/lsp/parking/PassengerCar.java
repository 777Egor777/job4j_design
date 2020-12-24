package ru.job4j.lsp.parking;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 23.12.2020
 */
public class PassengerCar implements Car {
    private final Car car;

    public PassengerCar(String name, String sign) {
        car = new CarBase(name, sign, 1);
    }

    @Override
    public int getId() {
        return car.getId();
    }

    @Override
    public int getSize() {
        return car.getSize();
    }

    @Override
    public int hashCode() {
        return car.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return car.equals(obj);
    }

    @Override
    public String toString() {
        return "PassengerCar" + car.toString();
    }
}
