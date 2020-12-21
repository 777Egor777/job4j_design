package ru.job4j.rsp;

import ru.job4j.ocp.FormatReport;
import ru.job4j.ocp.FormatReportEngine;
import ru.job4j.ocp.util.Tab;

import java.util.List;
import java.util.function.Predicate;

/**
 * Class that helps to
 * generate reports
 * about employees
 * in the company,
 * filtered by predicate.
 *
 * But this report is
 * in "old" format.
 *
 * @author Geraskin Egor
 * @version 2.0
 * @since 18.12.2020
 */
public class OldReport implements ReportEngine {
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
    public OldReport(Store store) {
        this.store = store;
        report = new FormatReport(store);
        init();
    }

    private void init() {
        report.setContentPrefix("Name;Hired;Fired;Salary;" + System.lineSeparator());
        report.setItemSuffix(";");
        report.setItemSeparator(System.lineSeparator());
        report.setFieldSeparator(";");
    }

    /**
     * Method generate report
     * in "old" format.
     * Employees filtered
     * by predicate
     *
     * @param filter - predicate.
     * @return - report in the
     *           "old" format
     */
    @Override
    public final String generate(Predicate<Employee> filter) {
        return report.generate(filter);
    }
}
