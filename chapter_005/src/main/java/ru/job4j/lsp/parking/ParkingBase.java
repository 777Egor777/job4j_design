package ru.job4j.lsp.parking;

import java.util.List;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 20.12.2020
 */
public class ParkingBase implements Parking {
    private final PlaceSet set;

    public ParkingBase(int... numOfPlacesOfEachType) {
        set = new PlaceSetBase(numOfPlacesOfEachType);
    }

    @Override
    public boolean park(Car car) {
        return false;
    }

    @Override
    public void free(Car car) {

    }

    @Override
    public List<Car> getCars() {
        return null;
    }
}
