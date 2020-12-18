package ru.job4j.ocp;

import ru.job4j.ocp.util.Tab;
import ru.job4j.rsp.Employee;
import ru.job4j.rsp.ReportEngine;
import ru.job4j.rsp.Store;

import java.util.List;

/**
 * Base interface for
 * extending ReportEngine
 * classes functionality.
 *
 * We will use it to make
 * many Report-classes
 * with different formats
 * of reports, but
 * using the same API
 * and with no
 * code-duplication.
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 17.12.2020
 */
public interface FormatReportEngine extends ReportEngine {
    /**
     * Setter for {@code contentPrefix}
     * field.
     * @param contentPrefix - new value of
     *                        {@code contentPrefix}
     *                        field
     */
    void setContentPrefix(String contentPrefix);

    /**
     * Setter for {@code contentSuffix}
     * field.
     * @param contentSuffix - new value of
     *                        {@code contentSuffix}
     *                        field
     */
    void setContentSuffix(String contentSuffix);

    /**
     * Setter for {@code itemPrefix}
     * field.
     * @param itemPrefix - new value of
     *                     {@code itemPrefix}
     *                     field
     */
    void setItemPrefix(String itemPrefix);

    /**
     * Setter for {@code itemSuffix}
     * field.
     * @param itemSuffix - new value of
     *                     {@code itemSuffix}
     *                     field
     */
    void setItemSuffix(String itemSuffix);

    /**
     * Setter for {@code fieldPrefix}
     * field.
     * @param fieldPrefix - new value of
     *                        {@code fieldPrefix}
     *                        field
     */
    void setFieldPrefix(String fieldPrefix);

    /**
     * Setter for {@code fieldSuffix}
     * field.
     * @param fieldSuffix - new value of
     *                        {@code fieldSuffix}
     *                        field
     */
    void setFieldSuffix(String fieldSuffix);

    /**
     * Setter for {@code itemSeparator}
     * field.
     * @param itemSeparator - new value of
     *                        {@code itemSeparator}
     *                        field
     */
    void setItemSeparator(String itemSeparator);

    /**
     * Setter for {@code fieldSeparator}
     * field.
     * @param fieldSeparator - new value of
     *                        {@code fieldSeparator}
     *                        field
     */
    void setFieldSeparator(String fieldSeparator);

    /**
     * Setter for {@code partTab}
     * field.
     * @param partTab - new value of
     *                        {@code partTab}
     *                        field
     */
    void setPartTab(String partTab);

    /**
     * Set count of part tabs
     * for each
     * item(record of employee).
     *
     * @param countOfTabs - number of tabs
     *                      in the whole tab value.
     */
    void setItemCountOfPartTabs(int countOfTabs);

    /**
     * Set count of part tabs
     * for each
     * field(part of item,
     * for example: "name": "Ivan").
     *
     * @param countOfTabs - number of tabs
     *                      in the whole tab value.
     */
    void setFieldCountOfPartTabs(int countOfTabs);

    /**
     * Getter of
     * content prefix.
     *
     * @return value of
     *         content prefix.
     */
    String getContentPrefix();

    /**
     * Getter of
     * content suffix.
     *
     * @return value of
     *         content suffix.
     */
    String getContentSuffix();

    /**
     * Getter of
     * item prefix.
     *
     * @return value of
     *         item prefix.
     */
    String getItemPrefix();

    /**
     * Getter of
     * item suffix.
     *
     * @return value of
     *         item suffix.
     */
    String getItemSuffix();

    /**
     * Getter of
     * field prefix.
     *
     * @return value of
     *         field prefix.
     */
    String getFieldPrefix();

    /**
     * Getter of
     * field suffix.
     *
     * @return value of
     *         field suffix.
     */
    String getFieldSuffix();

    /**
     * Getter of
     * item separator.
     *
     * @return value of
     *         item separator.
     */
    String getItemSeparator();

    /**
     * Getter of
     * field separator.
     *
     * @return value of
     *         field separator.
     */
    String getFieldSeparator();

    /**
     * Getter of
     * partTab.
     *
     * @return value of
     *         partTab.
     */
    String getPartTab();

    /**
     * Getter of count of
     * part tabs for each item.
     *
     * @return count of part
     *         tabs for each
     *         item.
     */
    int getItemCountOfPartTabs();

    /**
     * Getter of count of
     * part tabs for each field.
     *
     * @return count of part
     *         tabs for each
     *         field.
     */
    int getFieldCountOfPartTabs();

    /**
     * Getter for store,
     * from which we
     * receive user data.
     *
     * @return {@code Store} object,
     *         from which we receive
     *         user data.
     */
    Store getStore();

    /**
     * Return current tab
     * object.
     * @return current tab
     *         obj.
     */
    Tab getTab();

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

    String makeItem(List<String> fields);

    /**
     * Method make correct
     * {@code String}
     * representation of
     * each employee,
     * and then return it.
     *
     * @param emp - employee.
     * @return - correct {@code String}
     *           representation of the
     *           employee
     */
    String makeItem(FormatReportEngine engine, Employee emp);

    /**
     * Method make correct
     * report about
     * filtered employees
     * in {@code String}
     * format, and then
     * return it.
     *
     * @param items - list of correct
     *                {@code String}
     *                representations
     *                of employees.
     * @return final report.
     */
    String makeContent(List<String> items);
}
