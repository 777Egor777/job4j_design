package ru.job4j.lsp.food;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Objects;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ControlQualityTest {
    private static final long MS_PER_DAY = 24L * 60L * 60L * 1000L;
    private static final long MS_PER_SECOND = 1000L;
    private static Calendar now;
    public static Calendar create;
    public static Calendar expire;
    public static ControlQuality control;
    public static Food food;

    @Before
    public void doBeforeEachTest() {
        now = Calendar.getInstance();
        control = new ControlQuality();
        create = Calendar.getInstance();
        expire = Calendar.getInstance();
    }

    @Test
    public void whenAddToTrash() {
        Food food = new Food("Apple", now, now, 100, 40);
        control.add(food);
        Storage holder = control.getTrash();
        String result = Objects.requireNonNull(holder).toString();
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        joiner.add("Trash");
        joiner.add("Number of products: 1");
        joiner.add(food.toString());
        String expected =  joiner.toString();
        assertThat(result, is(expected));
    }

    @Test
    public void whenAddToShopWithDiscount() {
        create.setTimeInMillis(now.getTimeInMillis() - 4 * MS_PER_DAY);
        expire.setTimeInMillis(now.getTimeInMillis() + MS_PER_DAY);
        food = new Food("Apple", create, expire, 100, 40);
        control.add(food);
        Storage holder = control.getShop();
        String result = Objects.requireNonNull(holder).toString();
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        joiner.add("Shop");
        joiner.add("Number of products: 1");
        joiner.add(new Food("Apple", create, expire, 60, 0).toString());
        String expected =  joiner.toString();
        assertThat(result, is(expected));
    }

    @Test
    public void whenAddToShopWithNoDiscount() {
        create.setTimeInMillis(now.getTimeInMillis() - 2 * MS_PER_DAY);
        expire.setTimeInMillis(now.getTimeInMillis() + MS_PER_DAY);
        food = new Food("Apple", create, expire, 100, 40);
        control.add(food);
        Storage holder = control.getShop();
        String result = Objects.requireNonNull(holder).toString();
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        joiner.add("Shop");
        joiner.add("Number of products: 1");
        joiner.add(food.toString());
        String expected =  joiner.toString();
        assertThat(result, is(expected));
    }

    @Test
    public void whenAddToWarehouse() {
        create.setTimeInMillis(now.getTimeInMillis() - (long) (0.25 * MS_PER_DAY));
        expire.setTimeInMillis(now.getTimeInMillis() + MS_PER_DAY);
        Food food = new Food("Apple", create, expire, 100, 40);
        control.add(food);
        Storage holder = control.getWarehouse();
        String result = Objects.requireNonNull(holder).toString();
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        joiner.add("Warehouse");
        joiner.add("Number of products: 1");
        joiner.add(food.toString());
        String expected =  joiner.toString();
        assertThat(result, is(expected));
    }

    @Test
    public void resort() throws InterruptedException {
        create.setTimeInMillis(now.getTimeInMillis() - (long) (0.25 * MS_PER_SECOND));
        expire.setTimeInMillis(now.getTimeInMillis() + MS_PER_SECOND);
        Food food = new Food("Apple", create, expire, 100, 40);
        control.add(food);
        Thread.sleep(MS_PER_SECOND);
        control.resort();
        Storage holder = control.getTrash();
        String result = Objects.requireNonNull(holder).toString();
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        joiner.add("Trash");
        joiner.add("Number of products: 1");
        joiner.add(food.toString());
        String expected =  joiner.toString();
        assertThat(result, is(expected));
    }
}