package ru.job4j.rsp;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AccountantReportTest {

    @Test
    public void generate() {
        Store store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportEngine engine = new AccountantReport(store, 2);
        String result = engine.generate(emp -> true);
        String expected = new StringBuilder().append("Name;Hired;Fired;Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary() * 2).append(";")
                .toString();
        assertThat(result, is(expected));
    }
}