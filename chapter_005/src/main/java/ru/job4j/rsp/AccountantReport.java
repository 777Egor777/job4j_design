package ru.job4j.rsp;

import ru.job4j.ocp.FieldMaker;
import ru.job4j.ocp.FormatReport;
import ru.job4j.ocp.FormatReportEngine;
import ru.job4j.ocp.util.Tab;
import ru.job4j.rsp.util.Converter;
import ru.job4j.rsp.util.SalaryConverter;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class that helps to
 * generate reports
 * about employees
 * in the company,
 * filtered by predicate.
 *
 * This is report for
 * Accountant department.
 * Salary is multiple
 * to course.
 *
 * @author Geraskin Egor
 * @version 2.0
 * @since 18.12.2020
 */
public class AccountantReport implements ReportEngine, FieldMaker {
    private final FormatReport report;

    /**
     * We will multiple
     * salary of each
     * worker by this
     * course.
     */
    private final int course;

    /**
     * Converter that we will use
     * to convert salary value.
     */
    private final Converter converter;

    /**
     * Store, from which we
     * extract info about
     * employees.
     */
    private final Store store;

    /**
     *
     * @param course - init value of the
     *                {@code course} field.
     * @param store - init value of the
     *                {@code store} field.
     */
    public AccountantReport(Store store, int course) {
        this.store = store;
        this.course = course;
        converter = new SalaryConverter(course);
        report = new FormatReport(store);
        init();
    }

    private void init() {
        report.setFieldMaker(this);
        report.setContentPrefix("Name;Hired;Fired;Salary;" + System.lineSeparator());
        report.setItemSuffix(";");
        report.setItemSeparator(System.lineSeparator());
        report.setFieldSeparator(";");
    }

    /**
     * Method generate report
     * in format that
     * Accountant department
     * of the company asks.
     *
     * Employees filtered
     * by predicate.
     *
     * Salary is multiple
     * to course.
     *
     * @param filter - predicate.
     * @return report in "Accountant"
     *         format.
     */
    @Override
    public final String generate(Predicate<Employee> filter) {
        return report.generate(filter);
    }

    @Override
    public final String makeField(String key, String value) {
        String result;
        if (!key.equals("salary")) {
            result = report.makeField(key, value);
        } else {
            result = report.getTab().tab(report.getFieldCountOfPartTabs())
                    + report.getFieldPrefix()
                    + converter.convert(Double.parseDouble(value))
                    + report.getFieldSuffix();
        }
        return result;
    }
}
