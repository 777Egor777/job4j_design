package ru.job4j.rsp;

import java.util.function.Predicate;

/**
 * Interface that describes
 * generator of report
 * with employees in the company.
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 16.12.2020
 */
public interface ReportEngine {
    /**
     * Method generate and
     * return report about
     * employees of the
     * company, filtered
     * by predicate.
     *
     * @param filter - predicate.
     * @return report in {@code String}
     *         format.
     */
    String generate(Predicate<Employee> filter);
}
