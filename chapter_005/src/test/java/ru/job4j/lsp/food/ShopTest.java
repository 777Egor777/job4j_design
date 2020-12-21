package ru.job4j.lsp.food;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ShopTest {

    @Test
    public void whenAddWithDiscount() {
        Storage storage = new Shop();
        Calendar create = Calendar.getInstance();
        Calendar expire = Calendar.getInstance();
        create.setTimeInMillis(create.getTimeInMillis()  - 1000);
        expire.setTimeInMillis(expire.getTimeInMillis() + 100);
        Food food = new Food("Apple", create, expire, 50, 50);
        Food food2 = new Food("Apple", create, expire, 25.0, 0);
        storage.add(food);
        assertThat(storage.getAll().size(), is(1));
        assertThat(storage.getAll().get(0), is(food2));
    }

    @Test
    public void whenAddWithNoDiscount() {
        Storage storage = new Shop();
        Calendar create = Calendar.getInstance();
        Calendar expire = Calendar.getInstance();
        create.setTimeInMillis(create.getTimeInMillis()  - 1000);
        expire.setTimeInMillis(expire.getTimeInMillis() + 1000);
        Food food = new Food("Apple", create, expire, 50, 50);
        Food food2 = new Food("Apple", create, expire, 50, 50);
        storage.add(food);
        assertThat(storage.getAll().size(), is(1));
        assertThat(storage.getAll().get(0), is(food2));
    }

    @Test
    public void acceptWithDiscount() {
        Storage storage = new Shop();
        Calendar create = Calendar.getInstance();
        Calendar expire = Calendar.getInstance();
        create.setTimeInMillis(create.getTimeInMillis()  - 1000);
        expire.setTimeInMillis(expire.getTimeInMillis() + 100);
        Food food = new Food("Apple", create, expire, 50, 50);
        assertTrue(storage.accept(food));
    }

    @Test
    public void acceptWithNoDiscount() {
        Storage storage = new Shop();
        Calendar create = Calendar.getInstance();
        Calendar expire = Calendar.getInstance();
        create.setTimeInMillis(create.getTimeInMillis()  - 1000);
        expire.setTimeInMillis(expire.getTimeInMillis() + 1000);
        Food food = new Food("Apple", create, expire, 50, 50);
        assertTrue(storage.accept(food));
    }
}