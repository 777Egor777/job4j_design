package ru.job4j.lsp.parking;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CarTest {

    @Test
    public void getId() {
        Car truck = new Truck("", "", 5);
        Car car = new PassengerCar("", "");
        assertThat(car.getId(), is(truck.getId() + 1));
    }

    @Test
    public void getSizeWhenPassengerCar() {
        Car car = new PassengerCar("", "");
        assertThat(car.getSize(), is(1));
    }

    @Test
    public void getSizeWhenTruck() {
        int size = 3;
        Car car = new Truck("", "", size);
        assertThat(car.getSize(), is(size));
    }
}