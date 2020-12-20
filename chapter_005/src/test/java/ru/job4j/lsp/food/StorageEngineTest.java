package ru.job4j.lsp.food;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StorageEngineTest {

    @Test
    public void add() {
        Storage shop = new StorageEngine("Shop", food -> true);
        Calendar now = Calendar.getInstance();
        Food food = new Food("Apple", now, now, 100, 40);
        shop.add(food);
        assertThat(shop.clear().get(0).toString(),
                is(
                        String.format("Food{%s,%s,%s,%f,%f}",
                                "Apple",
                                now, now, 100.0, 40.0)
                ));
    }
}