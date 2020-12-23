package ru.job4j.lsp.parking;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParkingTest {

    @Test
    public void whenEnoughPassengerPlaceForLightweightCar() {
        Parking parking = new ParkingBase(1, 1);
        assertTrue(parking.park(new PassengerCar("", "")));
    }

    @Test
    public void whenNotEnoughPassengerPlaceForLightweightCar() {
        Parking parking = new ParkingBase(1, 1);
        parking.park(new PassengerCar("", ""));
        assertFalse(parking.park(new PassengerCar("", "")));
    }

    @Test
    public void whenNotEnoughPassengerPlaceForLightweightCar2() {
        Parking parking = new ParkingBase(0, 1);
        assertFalse(parking.park(new PassengerCar("", "")));
    }

    @Test
    public void whenEnoughPlaceForTruck() {
        Parking parking = new ParkingBase(0, 1);
        assertTrue(parking.park(new Truck("", "", 3)));
    }

    @Test
    public void whenEnoughPlaceForTruck2() {
        Parking parking = new ParkingBase(3, 0);
        assertTrue(parking.park(new Truck("", "", 3)));
    }

    @Test
    public void whenNotEnoughPlaceForTruck() {
        Parking parking = new ParkingBase(0, 1);
        parking.park(new Truck("", "", 2));
        assertFalse(parking.park(new Truck("", "", 3)));
    }

    @Test
    public void whenNotEnoughPlaceForTruck2() {
        Parking parking = new ParkingBase(3, 1);
        parking.park(new Truck("", "", 4));
        parking.park(new PassengerCar("", ""));
        assertFalse(parking.park(new Truck("", "", 3)));
    }

    @Test
    public void remove() {
        Parking parking = new ParkingBase(1, 1);
        Car car = new PassengerCar("", "");
        parking.park(car);
        parking.remove(car.getId());
        assertTrue(parking.getCars().isEmpty());
    }

    @Test
    public void getCars() {
        Parking parking = new ParkingBase(1, 1);
        Car car = new PassengerCar("", "");
        parking.park(car);
        assertThat(parking.getCars().get(0), is(car));
    }
}