package ru.job4j.lsp.parking;

import java.util.List;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 20.12.2020
 */
public interface Car {
    List<Place> getPlaces();
    boolean isParked();
    void park();
    void freePlace();
}
