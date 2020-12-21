package ru.job4j.rsp;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OldReportTest {
    private static Store store;
    private static Calendar now;
    private static Employee worker;
    private static ReportEngine report;

    @Before
    public void doBeforeEachTest() {
        store = new MemStore();
        now = Calendar.getInstance();
        worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        report = new OldReport(store);
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
        String result = report.generate(employee -> true);
        assertThat(result, is(expected));
    }
}