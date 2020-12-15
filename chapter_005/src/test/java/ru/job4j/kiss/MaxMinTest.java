package ru.job4j.kiss;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void max() {
        List<Integer> list = List.of(4, 1, 2, 1000, 12, 13, 11111, 2, 1000000);
        Comparator<Integer> cmp = Integer::compareTo;
        Integer result = new MaxMin().max(list, cmp);
        Integer expected = 1000000;
        assertThat(result, is(expected));
    }

    @Test
    public void min() {
        List<Integer> list = List.of(4, 1, 2, 1000, 12, 13, 11111, 2, 1000000);
        Comparator<Integer> cmp = Integer::compareTo;
        Integer result = new MaxMin().min(list, cmp);
        Integer expected = 1;
        assertThat(result, is(expected));
    }
}