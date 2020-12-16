package ru.job4j.rsp;

import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;

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
 * @version 1.0
 * @since 16.12.2020
 */
public class ReportHR implements ReportEngine {
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
    public String generate(Predicate<Employee> filter) {
        List<Employee> list = store.findBy(filter);
        list.sort(employeeCmpDescBySalary);
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        joiner.add("Name;Salary;");
        for (Employee worker : list) {
            StringBuilder builder = new StringBuilder();
            builder
                    .append(worker.getName()).append(";")
                    .append(worker.getSalary()).append(";");
            joiner.add(builder.toString());
        }
        return joiner.toString();
    }
}
