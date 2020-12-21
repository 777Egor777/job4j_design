package ru.job4j.ocp;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.rsp.Employee;
import ru.job4j.rsp.MemStore;
import ru.job4j.rsp.ReportEngine;
import ru.job4j.rsp.Store;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ReportXMLTest {
    private static Store store;
    private static Calendar now;
    private static Employee ivan;
    private static ReportEngine report;
    private static FieldMaker maker;

    @Before
    public void doBeforeEachTest() {
        store = new MemStore();
        now = Calendar.getInstance();
        ivan = new Employee("Ivan", now, now, 100);
        store.add(ivan);
        report = new ReportXML(store);
        maker = (FieldMaker) report;
    }


    @Test
    public void generate() {
        String result = report.generate(emp -> true);
        StringBuilder builder =  new StringBuilder();
        builder
                .append("<?xml version=\"1.0\"?>").append(System.lineSeparator())
                .append("<employees>").append(System.lineSeparator())
                .append("  <employee>").append(System.lineSeparator())
                .append("    <name>").append(ivan.getName()).append("</name>").append(System.lineSeparator())
                .append("    <hired>").append(ivan.getHired()).append("</hired>").append(System.lineSeparator())
                .append("    <fired>").append(ivan.getFired()).append("</fired>").append(System.lineSeparator())
                .append("    <salary>").append(ivan.getSalary()).append("</salary>").append(System.lineSeparator())
                .append("  </employee>").append(System.lineSeparator())
                .append("</employees>");
        String expected = builder.toString();
        assertThat(result, is(expected));
    }

    @Test
    public void makeField() {
        String result = maker.makeField("key", "value");
        String expected = "    <key>value</key>";
        assertThat(result, is(expected));
    }
}