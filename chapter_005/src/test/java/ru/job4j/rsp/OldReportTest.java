package ru.job4j.rsp;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OldReportTest {

    @Test
    public void generate() {
        Store store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportEngine engine = new OldReport(store);
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
}