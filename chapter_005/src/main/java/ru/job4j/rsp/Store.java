package ru.job4j.rsp;

import java.util.List;
import java.util.function.Predicate;

/**
 * Interface that describes
 * data-holder
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 16.12.2020
 */
public interface Store {
    /**
     * Method extract from
     * Store data as {@code List}
     * of Employees,
     * filtered by predicate
     *
     * @param filter - predicate
     * @return list of employees,
     *         filtered by predicate
     */
    List<Employee> findBy(Predicate<Employee> filter);

    /**
     * Method add new employee
     * to the Store.
     *
     * @param employee - new worker.
     */
    void add(Employee employee);
}
