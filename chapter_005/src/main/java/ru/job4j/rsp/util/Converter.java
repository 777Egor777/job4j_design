package ru.job4j.rsp.util;

/**
 * Interface that describes
 * action of converting
 * number.
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 17.12.2020
 */
public interface Converter {
    /**
     * Method that receive
     * number and
     * convert it.
     *
     * @param number - received number.
     * @return converted number.
     */
    double convert(double number);
}
