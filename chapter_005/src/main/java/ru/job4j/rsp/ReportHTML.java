package ru.job4j.rsp;

import java.util.List;
import java.util.function.Predicate;

/**
 * Method generate report
 * in format that
 * IT department
 * of the company asks -
 * specifically as
 * HTML table.
 *
 * Employees filtered
 * by predicate.
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 16.12.2020
 */
public class ReportHTML implements ReportEngine {
    /**
     * Store, from which we
     * extract info about
     * employees.
     */
    private final Store store;

    /**
     * Constructor.
     * @param store - init value of the
     *                {@code store} field.
     */
    public ReportHTML(Store store) {
        this.store = store;
    }

    /**
     * Method generate report
     * in HTML format
     * (as HTML - table).
     *
     * Employees filtered
     * by predicate.
     * @param filter - predicate.
     * @return report as HTML
     *         table.
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> list = store.findBy(filter);
        StringBuilder builder = new StringBuilder();
        builder.append("<table><tr><th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th></tr>");
        for (Employee worker : list) {
            builder.append("<tr>")
                    .append("<td>").append(worker.getName()).append("</td>")
                    .append("<td>").append(worker.getHired()).append("</td>")
                    .append("<td>").append(worker.getFired()).append("</td>")
                    .append("<td>").append(worker.getSalary()).append("</td>")
                    .append("</tr>");
        }
        builder.append("</table>");
        return builder.toString();
    }
}
