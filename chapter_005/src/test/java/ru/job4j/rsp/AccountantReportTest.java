package ru.job4j.rsp;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.ocp.FieldMaker;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AccountantReportTest {
    private static Store store;
    private static Calendar now;
    private static Employee worker;
    private static ReportEngine report;
    private static FieldMaker maker;

    @Before
    public void doBeforeEachTest() {
        store = new MemStore();
        now = Calendar.getInstance();
        worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        report = new AccountantReport(store, 2);
        maker = (FieldMaker) report;
    }

    @Test
    public void generate() {
        String result = report.generate(emp -> true);
        String expected = new StringBuilder().append("Name;Hired;Fired;Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary() * 2).append(";")
                .toString();
        assertThat(result, is(expected));
    }

    @Test
    public void makeField() {
        String key = "key";
        String value = "value";
        String result = maker.makeField(key, value);
        String expected = "value";
        assertThat(result, is(expected));
    }
}