package ru.job4j.rsp;

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
 * But this report is
 * in "old" format.
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 16.12.2020
 */
public class OldReport implements ReportEngine {
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
                    .append(worker.getSalary()).append(";");
            joiner.add(builder.toString());
        }
        return joiner.toString();
    }
}
