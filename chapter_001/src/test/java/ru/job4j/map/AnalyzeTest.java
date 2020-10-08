package ru.job4j.map;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalyzeTest {

    @Test
    public void diff() {
        assertThat(
                new Analyze().diff(
                        List.of(
                                new Analyze.User(1, "Egor"),
                                new Analyze.User(2, "Ivan"),
                                new Analyze.User(3, "Petr")
                        ),
                        List.of(
                                new Analyze.User(1, "Alex"),
                                new Analyze.User(10, "Viktor"),
                                new Analyze.User(3, "Petr")
                        )
                ), is(new Analyze.Info(1, 1, 1))
        );
    }
}