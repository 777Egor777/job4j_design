package ru.job4j.rsp;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ReportHTMLTest {

    @Test
    public void generate() {
        Store store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportEngine engine = new ReportHTML(store);
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
}