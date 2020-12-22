package ru.job4j.tictactoe;

import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConsoleNewLineTest {

    @Test
    public void print() {
        var out = new ByteArrayOutputStream();
        new ConsoleNewLine().print(out);
        assertThat(out.toString(), is(System.lineSeparator()));
    }
}