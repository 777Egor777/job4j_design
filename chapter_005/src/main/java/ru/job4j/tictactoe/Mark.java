package ru.job4j.tictactoe;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 22.12.2020
 */
public interface Mark<T> {
    void print(T screen);
}