package ru.job4j.ocp;

import ru.job4j.rsp.Employee;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 21.12.2020
 */
public interface ItemMaker {

    /**
     * Method make correct
     * {@code String}
     * representation of
     * each employee,
     * and then return it.
     *
     * @param emp - employee.
     * @return - correct {@code String}
     * representation of the
     * employee
     */
    String makeItem(Employee emp);
}
