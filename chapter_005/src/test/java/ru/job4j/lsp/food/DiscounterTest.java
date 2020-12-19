package ru.job4j.lsp.food;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class DiscounterTest {

    @Test
    public void apply() {
        Calendar now = Calendar.getInstance();
        Food food = new Food("Apple", now, now, 100, 40);
        Discounter dsc = new Discounter();
        dsc.apply(food);
        assertEquals(food.getPrice(), 60.0, 0.001);
        assertEquals(food.getDiscount(), 0, 0.001);
    }
}