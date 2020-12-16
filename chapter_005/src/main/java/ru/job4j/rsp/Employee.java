package ru.job4j.rsp;

import java.util.Calendar;
import java.util.Objects;

/**
 * Class describes an employee
 * of the company
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 16.12.2020
 */
public class Employee {
    /**
     * Name of the employee.
     */
    private String name;

    /**
     * Date when employee hired.
     */
    private Calendar hired;

    /**
     * Date when employee fired.
     */
    private Calendar fired;

    /**
     * Salary of the employee.
     */
    private double salary;

    /**
     * Main constructor.
     * @param name - name
     * @param hired - hired date
     * @param fired - fired date
     * @param salary - salary amount
     */
    public Employee(String name, Calendar hired, Calendar fired, double salary) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
    }

    /**
     * Getter for {@code name} field
     * @return this.name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for {@code name} field
     * @param name - new value of {@code name}
     *               field
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for {@code hired} field
     * @return this.hired
     */
    public Calendar getHired() {
        return hired;
    }

    /**
     * Setter for {@code hired} field
     * @param hired - new value of {@code hired}
     *               field
     */
    public void setHired(Calendar hired) {
        this.hired = hired;
    }

    /**
     * Getter for {@code fired} field
     * @return this.fired
     */
    public Calendar getFired() {
        return fired;
    }

    /**
     * Setter for {@code fired} field
     * @param fired - new value of {@code fired}
     *               field
     */
    public void setFired(Calendar fired) {
        this.fired = fired;
    }

    /**
     * Getter for {@code salary} field
     * @return this.salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Setter for {@code salary} field
     * @param salary - new value of {@code salary}
     *               field
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Employee employee = (Employee) obj;
        return name.equals(employee.name);
    }
}
