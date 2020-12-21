package ru.job4j.lsp.food;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class TrashTest {

    @Test
    public void accept() {
        Storage storage = new Trash();
        Calendar now = Calendar.getInstance();
        Food food = new Food("Apple", now, now, 50, 50);
        assertTrue(storage.accept(food));
    }
}