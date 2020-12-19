package ru.job4j.lsp.parking;

import java.util.List;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 20.12.2020
 */
public interface PlaceSet {
    void canFill(PlaceType type, int numberOfPlaces);
    List<Place> fill(PlaceType type, int numberOfPlaces);
    void clear(List<Place> places);
}
