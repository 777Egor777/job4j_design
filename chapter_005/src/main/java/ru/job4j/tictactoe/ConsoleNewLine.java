package ru.job4j.tictactoe;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 22.12.2020
 */
public class ConsoleNewLine implements Mark<OutputStream> {
    @Override
    public void print(OutputStream screen) {
        try {
            screen.write(System.lineSeparator().getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
