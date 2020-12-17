package ru.job4j.rsp.util;

/**
 * Class that helps us
 * to convert salary
 * to given course
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 17.12.2020
 */
public class SalaryConverter implements Converter {
    /**
     * Course that we will us
     * to convert given salary.
     */
    private final int course;

    /**
     * Constructor.
     * @param course - init value of {@code course}
     *                 field.
     */
    public SalaryConverter(int course) {
        this.course = course;
    }

    /**
     * Method that convert salary
     * to given course, and
     * return final value.
     *
     * @param salary - given salary.
     * @return salary, converted
     *         to course.
     */
    public double convert(double salary) {
        return salary * course;
    }
}
