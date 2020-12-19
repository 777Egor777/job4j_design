package ru.job4j.lsp.parking;

import java.util.List;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 20.12.2020
 */
public class CarBase implements Car {
    public CarBase(int... numOfPlacesOfEachType) {
    }

    @Override
    public List<Place> getPlaces() {
        return null;
    }

    @Override
    public boolean isParked() {
        return false;
    }

    @Override
    public void park() {

    }

    @Override
    public void freePlace() {

    }
}
