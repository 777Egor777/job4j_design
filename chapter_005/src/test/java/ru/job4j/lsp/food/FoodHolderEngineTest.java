package ru.job4j.lsp.food;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FoodHolderEngineTest {

    @Test
    public void add() {
        FoodHolder shop = new FoodHolderEngine("Shop");
        Calendar now = Calendar.getInstance();
        Food food = new Food("Apple", now, now, 100, 40);
        shop.add(food);
        assertThat(shop.getAll().get(0).toString(),
                is(
                        String.format("Food{%s,%s,%s,%f,%f}",
                                "Apple",
                                now, now, 100.0, 40.0)
                ));
    }
}