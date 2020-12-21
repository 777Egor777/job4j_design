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
    public static FormatReport report;

    @Before
    public void doBeforeTest() {
        store = new MemStore();
        now = Calendar.getInstance();
        ivan = new Employee("Ivan", now, now, 200);
        store.add(ivan);
        report = new FormatReport(store);
    }

    @Test
    public void generate() {
        report.setFieldSeparator(";");
        report.setItemSuffix(";");
        String result = report.generate(emp -> true);
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
        report.setContentPrefix("!");
        String result = report.getContentPrefix();
        String expected = "!";
        assertThat(result, is(expected));
    }

    @Test
    public void getContentSuffix() {
        report.setContentSuffix("!");
        String result = report.getContentSuffix();
        String expected = "!";
        assertThat(result, is(expected));
    }

    @Test
    public void getItemPrefix() {
        report.setItemPrefix("!");
        String result = report.getItemPrefix();
        String expected = "!";
        assertThat(result, is(expected));
    }

    @Test
    public void getItemSuffix() {
        report.setItemSuffix("!");
        String result = report.getItemSuffix();
        String expected = "!";
        assertThat(result, is(expected));
    }

    @Test
    public void getFieldPrefix() {
        report.setFieldPrefix("!");
        String result = report.getFieldPrefix();
        String expected = "!";
        assertThat(result, is(expected));
    }

    @Test
    public void getFieldSuffix() {
        report.setFieldSuffix("!");
        String result = report.getFieldSuffix();
        String expected = "!";
        assertThat(result, is(expected));
    }

    @Test
    public void getItemSeparator() {
        report.setItemSeparator("!");
        String result = report.getItemSeparator();
        String expected = "!";
        assertThat(result, is(expected));
    }

    @Test
    public void getFieldSeparator() {
        report.setFieldSeparator("!");
        String result = report.getFieldSeparator();
        String expected = "!";
        assertThat(result, is(expected));
    }

    @Test
    public void getPartTab() {
        report.setPartTab("!");
        String result = report.getPartTab();
        String expected = "!";
        assertThat(result, is(expected));
    }

    @Test
    public void getItemCountOfPartTabs() {
        report.setItemCountOfPartTabs(2);
        int result = report.getItemCountOfPartTabs();
        int expected = 2;
        assertThat(result, is(expected));
    }

    @Test
    public void getFieldCountOfPartTabs() {
        report.setFieldCountOfPartTabs(2);
        int result = report.getFieldCountOfPartTabs();
        int expected = 2;
        assertThat(result, is(expected));
    }

    @Test
    public void makeField() {
        report.setFieldPrefix("<td>");
        report.setFieldSuffix("</td>");
        report.setFieldCountOfPartTabs(4);
        report.setPartTab(" ");
        String result = report.makeField("key", "value");
        String expected = "    <td>value</td>";
        assertThat(result, is(expected));
    }

    @Test
    public void makeItem() {
        report.setFieldPrefix("<td>");
        report.setFieldSuffix("</td>");
        report.setFieldCountOfPartTabs(4);
        report.setPartTab(" ");
        report.setFieldSeparator(";");
        report.setItemSuffix(";");
        report.setItemPrefix(";");
        String result = report.makeItem(ivan);
        String expected = ";    <td>" + ivan.getName() + "</td>;    <td>"
                + ivan.getHired() + "</td>;    <td>" + ivan.getFired()
                + "</td>;    <td>" + ivan.getSalary() + "</td>;";
        assertThat(result, is(expected));
    }

    @Test
    public void makeContent() {
        report.setFieldPrefix("<td>");
        report.setFieldSuffix("</td>");
        report.setFieldCountOfPartTabs(4);
        report.setPartTab(" ");
        report.setFieldSeparator(";");
        report.setItemSuffix(";");
        report.setItemPrefix(";");
        List<String> items = new ArrayList<>();
        items.add(report.makeItem(ivan));
        String result = report.makeContent(items);
        String expected = items.get(0);
        assertThat(result, is(expected));
    }
}