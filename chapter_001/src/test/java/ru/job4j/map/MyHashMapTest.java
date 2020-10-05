package ru.job4j.map;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class    MyHashMapTest {

    @Test
    public void insert() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.insert("Egor", 25);
        assertThat(map.get("Egor"), is(25));
    }

    @Test
    public void get() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.insert("Egor", 25);
        assertNull(map.get("Ivan"));
    }

    @Test
    public void delete() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.insert("Egor", 25);
        map.delete("Egor");
        assertNull(map.get("Egor"));
    }
}