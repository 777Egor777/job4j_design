package ru.job4j.rsp;

import ru.job4j.rsp.util.Converter;
import ru.job4j.rsp.util.SalaryConverter;

import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;

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
 * @version 1.0
 * @since 16.12.2020
 */
public class AccountantReport implements ReportEngine {
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
    public String generate(Predicate<Employee> filter) {
        List<Employee> list = store.findBy(filter);
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        joiner.add("Name;Hired;Fired;Salary;");
        for (Employee worker : list) {
            StringBuilder builder = new StringBuilder();
            builder
                    .append(worker.getName()).append(";")
                    .append(worker.getHired()).append(";")
                    .append(worker.getFired()).append(";")
                    .append(converter.convert(worker.getSalary())).append(";");
            joiner.add(builder.toString());
        }
        return joiner.toString();
    }
}
