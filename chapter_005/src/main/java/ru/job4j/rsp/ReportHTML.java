package ru.job4j.rsp;

import ru.job4j.ocp.FormatReport;
import ru.job4j.ocp.FormatReportEngine;
import ru.job4j.ocp.util.Tab;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class that
 * helps to
 * generate report
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
 * @version 2.0
 * @since 18.12.2020
 */
public class ReportHTML implements ReportEngine {
    private final FormatReport report;

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
        report = new FormatReport(store);
        init();
    }

    private void init() {
        report.setFieldPrefix("<td>");
        report.setFieldSuffix("</td>");
        report.setItemPrefix("<tr>");
        report.setItemSuffix("</tr>");
        report.setContentPrefix("<table><tr><th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th></tr>");
        report.setContentSuffix("</table>");
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
    public final String generate(Predicate<Employee> filter) {
        return report.generate(filter);
    }
}
