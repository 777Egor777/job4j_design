package ru.job4j.lsp.food.repeat;

import org.checkerframework.checker.units.qual.C;
import org.junit.Before;
import org.junit.Test;
import org.quartz.SchedulerException;
import ru.job4j.lsp.food.ControlQuality;
import ru.job4j.lsp.food.Food;
import ru.job4j.lsp.food.Storage;

import java.io.IOException;
import java.util.Calendar;
import java.util.Objects;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RepeatEngineTest {
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
    public void repeatForever() {
        create.setTimeInMillis(now.getTimeInMillis() - (long) (0.25 * MS_PER_SECOND * 7));
        expire.setTimeInMillis(now.getTimeInMillis() + MS_PER_SECOND * 7);
        Food food = new Food("Apple", create, expire, 100, 40);
        control.add(food);
        try (Repeatable repeatable = new RepeatEngine(control)) {
            repeatable.repeatForever(8);
            Thread.sleep(9 * MS_PER_SECOND);
            Storage holder = control.getTrash();
            String result = Objects.requireNonNull(holder).toString();
            StringJoiner joiner = new StringJoiner(System.lineSeparator());
            joiner.add("Trash");
            joiner.add("Number of products: 1");
            joiner.add(food.toString());
            String expected =  joiner.toString();
            assertThat(result, is(expected));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test(expected = IllegalCallerException.class)
    public void whenMultiCallToRepeatForever() throws IOException, InterruptedException {
        create.setTimeInMillis(now.getTimeInMillis() - (long) (0.25 * MS_PER_SECOND * 7));
        expire.setTimeInMillis(now.getTimeInMillis() + MS_PER_SECOND * 7);
        Food food = new Food("Apple", create, expire, 100, 40);
        control.add(food);
        try (Repeatable repeatable = new RepeatEngine(control)) {
            repeatable.repeatForever(8);
            Thread.sleep(MS_PER_SECOND);
            repeatable.repeatForever(8);
            Thread.sleep(9 * MS_PER_SECOND);
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
}