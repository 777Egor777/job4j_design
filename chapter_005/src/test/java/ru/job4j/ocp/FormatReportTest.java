package ru.job4j.ocp;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.rsp.Employee;
import ru.job4j.rsp.MemStore;
import ru.job4j.rsp.Store;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FormatReportTest {
    public static Store store;
    public static Calendar now;
    public static Employee ivan;
    public static FormatReportEngine engine;

    @Before
    public void doBeforeTest() {
        store = new MemStore();
        now = Calendar.getInstance();
        ivan = new Employee("Ivan", now, now, 200);
        store.add(ivan);
        engine = new FormatReport(store);
    }

    @Test
    public void generate() {
        engine.setFieldSeparator(";");
        engine.setItemSuffix(";");
        String result = engine.generate(emp -> true);
        String expected = new StringBuilder()
                .append(ivan.getName()).append(";")
                .append(ivan.getHired()).append(";")
                .append(ivan.getFired()).append(";")
                .append(ivan.getSalary()).append(";")
                .toString();
        assertThat(result, is(expected));
    }

    @Test
    public void getContentPrefix() {
        engine.setContentPrefix("!");
        String result = engine.getContentPrefix();
        String expected = "!";
        assertThat(result, is(expected));
    }

    @Test
    public void getContentSuffix() {
        engine.setContentSuffix("!");
        String result = engine.getContentSuffix();
        String expected = "!";
        assertThat(result, is(expected));
    }

    @Test
    public void getItemPrefix() {
        engine.setItemPrefix("!");
        String result = engine.getItemPrefix();
        String expected = "!";
        assertThat(result, is(expected));
    }

    @Test
    public void getItemSuffix() {
        engine.setItemSuffix("!");
        String result = engine.getItemSuffix();
        String expected = "!";
        assertThat(result, is(expected));
    }

    @Test
    public void getFieldPrefix() {
        engine.setFieldPrefix("!");
        String result = engine.getFieldPrefix();
        String expected = "!";
        assertThat(result, is(expected));
    }

    @Test
    public void getFieldSuffix() {
        engine.setFieldSuffix("!");
        String result = engine.getFieldSuffix();
        String expected = "!";
        assertThat(result, is(expected));
    }

    @Test
    public void getItemSeparator() {
        engine.setItemSeparator("!");
        String result = engine.getItemSeparator();
        String expected = "!";
        assertThat(result, is(expected));
    }

    @Test
    public void getFieldSeparator() {
        engine.setFieldSeparator("!");
        String result = engine.getFieldSeparator();
        String expected = "!";
        assertThat(result, is(expected));
    }

    @Test
    public void getPartTab() {
        engine.setPartTab("!");
        String result = engine.getPartTab();
        String expected = "!";
        assertThat(result, is(expected));
    }

    @Test
    public void getItemCountOfPartTabs() {
        engine.setItemCountOfPartTabs(2);
        int result = engine.getItemCountOfPartTabs();
        int expected = 2;
        assertThat(result, is(expected));
    }

    @Test
    public void getFieldCountOfPartTabs() {
        engine.setFieldCountOfPartTabs(2);
        int result = engine.getFieldCountOfPartTabs();
        int expected = 2;
        assertThat(result, is(expected));
    }

    @Test
    public void makeField() {
        engine.setFieldPrefix("<td>");
        engine.setFieldSuffix("</td>");
        engine.setFieldCountOfPartTabs(4);
        engine.setPartTab(" ");
        String result = engine.makeField("key", "value");
        String expected = "    <td>value</td>";
        assertThat(result, is(expected));
    }

    @Test
    public void makeItem() {
        engine.setFieldPrefix("<td>");
        engine.setFieldSuffix("</td>");
        engine.setFieldCountOfPartTabs(4);
        engine.setPartTab(" ");
        engine.setFieldSeparator(";");
        engine.setItemSuffix(";");
        engine.setItemPrefix(";");
        String result = engine.makeItem(engine, ivan);
        String expected = ";    <td>" + ivan.getName() + "</td>;    <td>"
                + ivan.getHired() + "</td>;    <td>" + ivan.getFired()
                + "</td>;    <td>" + ivan.getSalary() + "</td>;";
        assertThat(result, is(expected));
    }

    @Test
    public void makeContent() {
        engine.setFieldPrefix("<td>");
        engine.setFieldSuffix("</td>");
        engine.setFieldCountOfPartTabs(4);
        engine.setPartTab(" ");
        engine.setFieldSeparator(";");
        engine.setItemSuffix(";");
        engine.setItemPrefix(";");
        List<String> items = new ArrayList<>();
        items.add(engine.makeItem(engine, ivan));
        String result = engine.makeContent(items);
        String expected = items.get(0);
        assertThat(result, is(expected));
    }
}