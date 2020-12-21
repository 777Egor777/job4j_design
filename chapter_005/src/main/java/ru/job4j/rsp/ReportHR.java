package ru.job4j.rsp;

import ru.job4j.ocp.FormatReport;
import ru.job4j.ocp.FormatReportEngine;
import ru.job4j.ocp.ItemMaker;
import ru.job4j.ocp.util.Tab;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Method generate report
 * in format that
 * HR department
 * of the company asks.
 *
 * Employees filtered
 * by predicate.
 *
 * Employees sorted by
 * desc of salary.
 *
 * Dates when employee
 * hired and fired
 * removed from the
 * report.
 *
 * @author Geraskin Egor
 * @version 2.0
 * @since 18.12.2020
 */
public class ReportHR implements ReportEngine, ItemMaker {
    private final FormatReport report;

    /**
     * Store, from which we
     * extract info about
     * employees.
     */
    private final Store store;

    /**
     * Comparator for {@code Employee}
     * objects that we will use
     * to sort employees
     * desc by salary.
     */
    private final Comparator<Employee> employeeCmpDescBySalary = (o1, o2) -> Double.compare(o1.getSalary(), o2.getSalary()) * -1;


    /**
     * Constructor.
     * @param store - init value of the
     *                {@code store} field.
     */
    public ReportHR(Store store) {
        this.store = store;
        report = new FormatReport(store);
        init();
    }

    private void init() {
        report.setItemMakerMaker(this);
        report.setContentPrefix("Name;Salary;" + System.lineSeparator());
        report.setItemSuffix(";");
        report.setItemSeparator(System.lineSeparator());
        report.setFieldSeparator(";");
    }

    /**
     * Method generate report
     * in format that
     * HR department
     * of the company asks.
     *
     * Employees filtered
     * by predicate.
     *
     * Employees sorted by
     * desc of salary.
     *
     * In this report there
     * are no dates when
     * employee hired
     * and fired.
     *
     * @param filter - predicate.
     * @return - report in "HR"
     *           format
     */
    @Override
    public final String generate(Predicate<Employee> filter) {
        return report.makeContent(store.findBy(filter).stream().sorted(employeeCmpDescBySalary).map(report::makeItem)
                .collect(Collectors.toList()));
    }

    @Override
    public final String makeItem(Employee emp) {
        List<String> fields = new ArrayList<>();
        fields.add(report.makeField("name", emp.getName()));
        fields.add(report.makeField("salary", "" + emp.getSalary()));
        return report.makeItem(fields);
    }
}
