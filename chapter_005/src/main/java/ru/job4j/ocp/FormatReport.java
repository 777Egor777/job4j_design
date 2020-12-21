package ru.job4j.ocp;

import ru.job4j.ocp.util.Tab;
import ru.job4j.ocp.util.TabFactory;
import ru.job4j.rsp.Employee;
import ru.job4j.rsp.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * We will use this
 * class only in
 * aggregation with
 * many other Report -
 * classes.
 *
 * So it's just an
 * auxiliary class,
 * that helps us
 * to avoid code
 * duplicate.
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 17.12.2020
 */
public class FormatReport implements FormatReportEngine {
    private final static int CAPACITY_TAB_FACTORY = 10;
    private FieldMaker fieldMaker = this;
    private ItemMaker itemMaker = this;

    /**
     * Prefix of the final
     * report {@code String}
     * representation.
     */
    private String contentPrefix = "";

    /**
     * Suffix of the final
     * report {@code String}
     * representation.
     */
    private String contentSuffix = "";

    /**
     * Prefix of each
     * employee's {@code String}
     * representation.
     */
    private String itemPrefix = "";

    /**
     * Suffix of each
     * employee's {@code String}
     * representation.
     */
    private String itemSuffix = "";

    /**
     * Prefix of each
     * field's {@code String}
     * representation.
     */
    private String fieldPrefix = "";

    /**
     * Suffix of each
     * field's {@code String}
     * representation.
     */
    private String fieldSuffix = "";

    /**
     * Separator for items.
     */
    private String itemSeparator = "";

    /**
     * Separator for fields.
     */
    private String fieldSeparator = "";

    /**
     * Part tab for items
     * and fields.
     */
    private String partTab = "";

    /**
     * Count of part tabs
     * for each item.
     */
    private int itemCountOfPartTabs = 0;

    /**
     * Count of part tabs
     * for each field.
     */
    private int fieldCountOfPartTabs = 0;

    /**
     * Current {@code Tab}
     * object, that
     * helps us to
     * receive correct
     * tab for each
     * item and each
     * field.
     */
    private Tab tab = new TabFactory(partTab, CAPACITY_TAB_FACTORY);

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
    public FormatReport(Store store) {
        this.store = store;
    }

    public final void setFieldMaker(FieldMaker maker) {
        fieldMaker = maker;
    }

    public final void setItemMakerMaker(ItemMaker maker) {
        itemMaker = maker;
    }

    /**
     * Setter for {@code contentPrefix}
     * field.
     *
     * @param contentPrefix - new value of
     *                      {@code contentPrefix}
     *                      field
     */
    public final void setContentPrefix(String contentPrefix) {
        this.contentPrefix = contentPrefix;
    }

    /**
     * Setter for {@code contentSuffix}
     * field.
     *
     * @param contentSuffix - new value of
     *                      {@code contentSuffix}
     *                      field
     */
    public final void setContentSuffix(String contentSuffix) {
        this.contentSuffix = contentSuffix;
    }

    /**
     * Setter for {@code itemPrefix}
     * field.
     *
     * @param itemPrefix - new value of
     *                   {@code itemPrefix}
     *                   field
     */
    public final void setItemPrefix(String itemPrefix) {
        this.itemPrefix = itemPrefix;
    }

    /**
     * Setter for {@code itemSuffix}
     * field.
     *
     * @param itemSuffix - new value of
     *                   {@code itemSuffix}
     *                   field
     */
    public final void setItemSuffix(String itemSuffix) {
        this.itemSuffix = itemSuffix;
    }

    /**
     * Setter for {@code fieldPrefix}
     * field.
     *
     * @param fieldPrefix - new value of
     *                    {@code fieldPrefix}
     *                    field
     */
    public final void setFieldPrefix(String fieldPrefix) {
        this.fieldPrefix = fieldPrefix;
    }

    /**
     * Setter for {@code fieldSuffix}
     * field.
     *
     * @param fieldSuffix - new value of
     *                    {@code fieldSuffix}
     *                    field
     */
    public final void setFieldSuffix(String fieldSuffix) {
        this.fieldSuffix = fieldSuffix;
    }

    /**
     * Setter for {@code itemSeparator}
     * field.
     *
     * @param itemSeparator - new value of
     *                      {@code itemSeparator}
     *                      field
     */
    public final void setItemSeparator(String itemSeparator) {
        this.itemSeparator = itemSeparator;
    }

    /**
     * Setter for {@code fieldSeparator}
     * field.
     *
     * @param fieldSeparator - new value of
     *                       {@code fieldSeparator}
     *                       field
     */
    public final void setFieldSeparator(String fieldSeparator) {
        this.fieldSeparator = fieldSeparator;
    }

    /**
     * Setter for {@code partTab}
     * field.
     *
     * @param partTab - new value of
     *                {@code partTab}
     *                field
     */
    public final void setPartTab(String partTab) {
        this.partTab = partTab;
        tab = new TabFactory(partTab, CAPACITY_TAB_FACTORY);
    }

    /**
     * Set count of part
     * tabs for each
     * item(record of employee).
     *
     * @param countOfTabs - number of tabs
     */
    public final void setItemCountOfPartTabs(int countOfTabs) {
        this.itemCountOfPartTabs = countOfTabs;
    }

    /**
     * Set count of part tabs
     * for each
     * field(part of item,
     * for example: "name": "Ivan").
     *
     * @param countOfTabs - number of tabs
     */
    public final void setFieldCountOfPartTabs(int countOfTabs) {
        this.fieldCountOfPartTabs = countOfTabs;
    }

    /**
     * Getter of
     * content prefix.
     *
     * @return value of
     * content prefix.
     */
    public final String getContentPrefix() {
        return contentPrefix;
    }

    /**
     * Getter of
     * content suffix.
     *
     * @return value of
     * content suffix.
     */
    public final String getContentSuffix() {
        return contentSuffix;
    }

    /**
     * Getter of
     * item prefix.
     *
     * @return value of
     * item prefix.
     */
    public final String getItemPrefix() {
        return itemPrefix;
    }

    /**
     * Getter of
     * item suffix.
     *
     * @return value of
     * item suffix.
     */
    public final String getItemSuffix() {
        return itemSuffix;
    }

    /**
     * Getter of
     * field prefix.
     *
     * @return value of
     * field prefix.
     */
    public final String getFieldPrefix() {
        return fieldPrefix;
    }

    /**
     * Getter of
     * field suffix.
     *
     * @return value of
     * field suffix.
     */
    public final String getFieldSuffix() {
        return fieldSuffix;
    }

    /**
     * Getter of
     * item separator.
     *
     * @return value of
     * item separator.
     */
    public final String getItemSeparator() {
        return itemSeparator;
    }

    /**
     * Getter of
     * field separator.
     *
     * @return value of
     * field separator.
     */
    public final String getFieldSeparator() {
        return fieldSeparator;
    }

    /**
     * Getter of
     * partTab.
     *
     * @return value of
     * partTab.
     */
    public final String getPartTab() {
        return partTab;
    }

    /**
     * Getter of count of
     * part tabs for each item.
     *
     * @return count of part
     * tabs for each
     * item.
     */
    public final int getItemCountOfPartTabs() {
        return itemCountOfPartTabs;
    }

    /**
     * Getter of count of
     * part tabs for each field.
     *
     * @return count of part
     * tabs for each
     * field.
     */
    public final int getFieldCountOfPartTabs() {
        return fieldCountOfPartTabs;
    }


    /**
     * Getter for store,
     * from which we
     * receive user data.
     *
     * @return {@code Store} object,
     * from which we receive
     * user data.
     */
    public final Store getStore() {
        return store;
    }

    /**
     * Return current tab
     * object.
     *
     * @return current tab
     * obj.
     */
    public final Tab getTab() {
        return tab;
    }

    /**
     * Return {@code String}
     * representation of
     * each field of the
     * employee.
     * <p>
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
     * @param key   - key of the
     *              field.
     * @param value - value of the
     *              field.
     * @return - correct {@code String}
     * representation of the
     * field.
     */
    @Override
    public final String makeField(String key, String value) {
        return tab.tab(fieldCountOfPartTabs)
                + fieldPrefix
                + value
                + fieldSuffix;
    }

    public final String makeItem(List<String> fields) {
        return tab.tab(itemCountOfPartTabs)
                + itemPrefix
                + fields.stream().collect(Collectors.joining(fieldSeparator))
                + itemSuffix;
    }

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
    @Override
    public final String makeItem(Employee emp) {
        if (itemMaker == this) {
            List<String> fields = new ArrayList<>();
            fields.add(fieldMaker.makeField("name", emp.getName()));
            fields.add(fieldMaker.makeField("hired", "" + emp.getHired()));
            fields.add(fieldMaker.makeField("fired", "" + emp.getFired()));
            fields.add(fieldMaker.makeField("salary", "" + emp.getSalary()));
            return makeItem(fields);
        } else {
            return itemMaker.makeItem(emp);
        }
    }

    /**
     * Method make correct
     * report about
     * filtered employees
     * in {@code String}
     * format, and then
     * return it.
     *
     * @param items - list of correct
     *              {@code String}
     *              representations
     *              of employees.
     * @return final report.
     */
    public final String makeContent(List<String> items) {
        return contentPrefix
                + items.stream().collect(Collectors.joining(itemSeparator))
                + contentSuffix;
    }

    /**
     * Method generate and
     * return report about
     * employees of the
     * company, filtered
     * by predicate.
     *
     * @param filter - predicate.
     * @return report in {@code String}
     * format.
     */
    @Override
    public final String generate(Predicate<Employee> filter) {
        return makeContent(store.findBy(filter).stream().map(this::makeItem).collect(Collectors.toList()));
    }
}
