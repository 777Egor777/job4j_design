package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Test
    public void whenTwoPeriods() {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/when_two_periods_source.txt",
                            "./data/when_two_periods_target.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader("./data/when_two_periods_target.txt"))) {
            assertThat(reader.readLine() + System.lineSeparator() + reader.readLine(),
                       is("10:57:01;10:59:01" + System.lineSeparator() + "11:01:02;11:02:02"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}