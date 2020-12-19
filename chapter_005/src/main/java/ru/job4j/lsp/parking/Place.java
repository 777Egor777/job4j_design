package ru.job4j.lsp.parking;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 20.12.2020
 */
public interface Place {
    void clear();
    boolean isEmpty();
    void take();
}
