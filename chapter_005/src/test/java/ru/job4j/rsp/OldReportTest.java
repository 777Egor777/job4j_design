package ru.job4j.rsp;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.ocp.FormatReportEngine;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OldReportTest {
    private static Store store;
    private static Calendar now;
    private static Employee worker;
    private static FormatReportEngine engine;

    @Before
    public void doBeforeEachTest() {
        store = new MemStore();
        now = Calendar.getInstance();
        worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        engine = new OldReport(store);
    }

    @Test
    public void generate() {
        String expected = new StringBuilder().append("Name;Hired;Fired;Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .toString();
        String result = engine.generate(employee -> true);
        assertThat(result, is(expected));
    }

    @Test
    public void makeField() {
        String key = "key";
        String value = "value";
        String result = engine.makeField(key, value);
        String expected = "value";
        assertThat(result, is(expected));
    }

    @Test
    public void makeItem() {
        String result = engine.makeItem(engine, worker);
        String expected = String.format("%s;%s;%s;%s;",
                worker.getName(),
                worker.getHired(),
                worker.getFired(),
                worker.getSalary());
        assertThat(result, is(expected));
    }
}