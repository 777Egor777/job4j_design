package ru.job4j.cache;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SoftCacheTest {

    @Test
    public void get() {
        Cache cache = new SoftCache();
        String fileName = "get_test.txt";
        String expected = "Test";
        String result = cache.get(fileName);
        assertThat(result, is(expected));
    }

    @Test
    public void put() {
        Cache cache = new SoftCache();
        String fileName = "get_test.txt";
        cache.put(fileName, "Test2");
        String result = cache.get(fileName);
        String expected = "Test2";
        assertThat(result, is(expected));
    }
}