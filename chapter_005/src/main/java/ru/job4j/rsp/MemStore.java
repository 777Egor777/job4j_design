package ru.job4j.rsp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class implements {@code Store}
 * interface. Data holder which
 * use memory.
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 16.12.2020
 */
public class MemStore implements Store {
    /**
     * List of all employees
     * in the company.
     */
    private final List<Employee> employees = new ArrayList<>();

    /**
     * Method add new employee
     * to the employee's list
     * of the object
     *
     * @param employee - new employee
     */
    @Override
    public void add(Employee employee) {
        employees.add(employee);
    }

    /**
     * @param filter - predicate
     * @return list of employees,
     *         filtered by predicate
     */
    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return employees.stream().filter(filter).collect(Collectors.toList());
    }
}
