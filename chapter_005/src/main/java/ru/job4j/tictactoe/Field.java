package ru.job4j.tictactoe;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 22.12.2020
 */
public interface Field {
    ChipType get(int x, int y);
    void set(int x, int y, ChipType chip);
    boolean isGameOver();
}
