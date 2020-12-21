package ru.job4j.ocp;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 21.12.2020
 */
public interface FieldMaker {

    /**
     * Return {@code String}
     * representation of
     * each field of the
     * employee.
     *
     * For example, employee
     * has field
     * "name": "Ivan".
     * This method will
     * make correct
     * {@code String}
     * representation
     * of this field,
     * and then return it.
     *
     * @param key - key of the
     *              field.
     * @param value - value of the
     *                field.
     * @return - correct {@code String}
     *           representation of the
     *           field.
     */
    String makeField(String key, String value);
}
