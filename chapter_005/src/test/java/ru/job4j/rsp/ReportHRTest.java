package ru.job4j.rsp;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.ocp.ItemMaker;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ReportHRTest {
    private static Store store;
    private static Calendar now;
    private static Employee workerEgor;
    private static Employee workerIvan;
    public static ReportEngine report;
    public static ItemMaker maker;

    @Before
    public void doBeforeEachTest() {
        store = new MemStore();
        now = Calendar.getInstance();
        workerEgor = new Employee("Ivan", now, now, 100);
        workerIvan = new Employee("Ivan", now, now, 200);
        store.add(workerEgor);
        store.add(workerIvan);
        report = new ReportHR(store);
        maker = (ItemMaker) report;
    }

    @Test
    public void generate() {
        String result = report.generate(emp -> true);
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

    @Test
    public void makeItem() {
        String result = maker.makeItem(workerIvan);
        String expected = String.format("%s;%s;",
                workerIvan.getName(),
                workerIvan.getSalary());
        assertThat(result, is(expected));
    }
}