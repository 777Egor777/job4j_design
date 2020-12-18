package ru.job4j.rsp;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.ocp.FormatReportEngine;

import java.util.Calendar;
import java.util.Locale;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ReportHTMLTest {
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
        engine = new ReportHTML(store);
    }

    @Test
    public void generate() {
        String result = engine.generate(emp -> true);
        String expected = new StringBuilder()
                .append("<table><tr><th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th></tr><tr>")
                .append("<td>").append(worker.getName()).append("</td>")
                .append("<td>").append(worker.getHired()).append("</td>")
                .append("<td>").append(worker.getFired()).append("</td>")
                .append("<td>").append(worker.getSalary()).append("</td></tr></table>")
                .toString();
        assertThat(result, is(expected));
    }

    @Test
    public void makeField() {
        String result = engine.makeField("key", "value");
        String expected = "<td>value</td>";
        assertThat(result, is(expected));
    }

    @Test
    public void makeItem() {
        String result = engine.makeItem(engine, worker);
        String expected = new StringBuilder().append("<tr>")
                .append("<td>").append(worker.getName()).append("</td>")
                .append("<td>").append(worker.getHired()).append("</td>")
                .append("<td>").append(worker.getFired()).append("</td>")
                .append("<td>").append(worker.getSalary()).append("</td></tr>")
                        .toString();
        assertThat(result, is(expected));
    }
}