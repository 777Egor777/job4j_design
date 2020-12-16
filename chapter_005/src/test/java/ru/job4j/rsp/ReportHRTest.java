package ru.job4j.rsp;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ReportHRTest {

    @Test
    public void generate() {
        Store store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee workerEgor = new Employee("Egor", now, now, 100);
        Employee workerIvan = new Employee("Ivan", now, now, 200);
        store.add(workerEgor);
        store.add(workerIvan);
        ReportEngine engine = new ReportHR(store);
        String result = engine.generate(emp -> true);
        String expected = new StringBuilder().append("Name;Salary;")
                .append(System.lineSeparator())
                .append(workerIvan.getName()).append(";")
                .append(workerIvan.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(workerEgor.getName()).append(";")
                .append(workerEgor.getSalary()).append(";")
                .toString();
        assertThat(result, is(expected));
    }
}