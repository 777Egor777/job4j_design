package ru.job4j.lsp.parking;

import java.util.List;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 20.12.2020
 */
public class PlaceSetBase implements PlaceSet {

    public PlaceSetBase(int... numOfPlacesOfEachType) {
    }

    @Override
    public void canFill(PlaceType type, int numberOfPlaces) {

    }

    @Override
    public List<Place> fill(PlaceType type, int numberOfPlaces) {
        return null;
    }

    @Override
    public void clear(List<Place> places) {

    }
}
