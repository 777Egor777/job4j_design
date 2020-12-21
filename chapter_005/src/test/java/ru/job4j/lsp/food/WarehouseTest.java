package ru.job4j.lsp.food;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class WarehouseTest {

    @Test
    public void accept() {
        Storage storage = new Warehouse();
        Calendar create = Calendar.getInstance();
        Calendar expire = Calendar.getInstance();
        expire.setTimeInMillis(expire.getTimeInMillis() + 1000);
        Food food = new Food("Apple", create, expire, 50, 50);
        assertTrue(storage.accept(food));
    }
}