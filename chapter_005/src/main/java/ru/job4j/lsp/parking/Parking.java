package ru.job4j.lsp.parking;

import java.util.List;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 20.12.2020
 */
public interface Parking {
    boolean park(Car car);
    void free(Car car);
    List<Car> getCars();
}
