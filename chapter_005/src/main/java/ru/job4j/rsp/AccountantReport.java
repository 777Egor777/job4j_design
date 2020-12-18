package ru.job4j.rsp;

import ru.job4j.ocp.FormatReport;
import ru.job4j.ocp.FormatReportEngine;
import ru.job4j.ocp.util.Tab;
import ru.job4j.rsp.util.Converter;
import ru.job4j.rsp.util.SalaryConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class that helps to
 * generate reports
 * about employees
 * in the company,
 * filtered by predicate.
 *
 * This is report for
 * Accountant department.
 * Salary is multiple
 * to course.
 *
 * @author Geraskin Egor
 * @version 2.0
 * @since 18.12.2020
 */
public class AccountantReport implements FormatReportEngine {
    private final FormatReportEngine engine;

    /**
     * We will multiple
     * salary of each
     * worker by this
     * course.
     */
    private final int course;

    /**
     * Converter that we will use
     * to convert salary value.
     */
    private final Converter converter;

    /**
     * Store, from which we
     * extract info about
     * employees.
     */
    private final Store store;

    /**
     *
     * @param course - init value of the
     *                {@code course} field.
     * @param store - init value of the
     *                {@code store} field.
     */
    public AccountantReport(Store store, int course) {
        this.store = store;
        this.course = course;
        converter = new SalaryConverter(course);
        engine = new FormatReport(store);
        init();
    }

    private void init() {
        engine.setContentPrefix("Name;Hired;Fired;Salary;" + System.lineSeparator());
        engine.setItemSuffix(";");
        engine.setItemSeparator(System.lineSeparator());
        engine.setFieldSeparator(";");
    }

    /**
     * Method generate report
     * in format that
     * Accountant department
     * of the company asks.
     *
     * Employees filtered
     * by predicate.
     *
     * Salary is multiple
     * to course.
     *
     * @param filter - predicate.
     * @return report in "Accountant"
     *         format.
     */
    @Override
    public final String generate(Predicate<Employee> filter) {
        return makeContent(store.findBy(filter).stream().map(emp -> makeItem(this, emp))
                .collect(Collectors.toList()));
    }

    @Override
    public final void setContentPrefix(String contentPrefix) {
        engine.setContentPrefix(contentPrefix);
    }

    @Override
    public final void setContentSuffix(String contentSuffix) {
        engine.setContentSuffix(contentSuffix);
    }

    @Override
    public final void setItemPrefix(String itemPrefix) {
        engine.setItemPrefix(itemPrefix);
    }

    @Override
    public final void setItemSuffix(String itemSuffix) {
        engine.setItemSuffix(itemSuffix);
    }

    @Override
    public final void setFieldPrefix(String fieldPrefix) {
        engine.setFieldPrefix(fieldPrefix);
    }

    @Override
    public final void setFieldSuffix(String fieldSuffix) {
        engine.setFieldSuffix(fieldSuffix);
    }

    @Override
    public final void setItemSeparator(String itemSeparator) {
        engine.setItemSeparator(itemSeparator);
    }

    @Override
    public final void setFieldSeparator(String fieldSeparator) {
        engine.setFieldSeparator(fieldSeparator);
    }

    @Override
    public final void setPartTab(String partTab) {
        engine.setPartTab(partTab);
    }

    @Override
    public final void setItemCountOfPartTabs(int countOfTabs) {
        engine.setItemCountOfPartTabs(countOfTabs);
    }

    @Override
    public final void setFieldCountOfPartTabs(int countOfTabs) {
        engine.setFieldCountOfPartTabs(countOfTabs);
    }

    @Override
    public final String getContentPrefix() {
        return engine.getContentPrefix();
    }

    @Override
    public final String getContentSuffix() {
        return engine.getContentSuffix();
    }

    @Override
    public final String getItemPrefix() {
        return engine.getItemPrefix();
    }

    @Override
    public final String getItemSuffix() {
        return engine.getItemSuffix();
    }

    @Override
    public final String getFieldPrefix() {
        return engine.getFieldPrefix();
    }

    @Override
    public final String getFieldSuffix() {
        return engine.getFieldSuffix();
    }

    @Override
    public final String getItemSeparator() {
        return engine.getItemSeparator();
    }

    @Override
    public final String getFieldSeparator() {
        return engine.getFieldSeparator();
    }

    @Override
    public final String getPartTab() {
        return engine.getPartTab();
    }

    @Override
    public final int getItemCountOfPartTabs() {
        return engine.getItemCountOfPartTabs();
    }

    @Override
    public final int getFieldCountOfPartTabs() {
        return engine.getFieldCountOfPartTabs();
    }

    @Override
    public final Store getStore() {
        return engine.getStore();
    }

    @Override
    public final Tab getTab() {
        return engine.getTab();
    }

    @Override
    public final String makeField(String key, String value) {
        String result = "";
        if (!key.equals("salary")) {
            result = engine.makeField(key, value);
        } else {
            result = getTab().tab(getFieldCountOfPartTabs())
                    + getFieldPrefix()
                    + converter.convert(Double.parseDouble(value))
                    + getFieldSuffix();
        }
        return result;
    }

    @Override
    public final String makeItem(List<String> fields) {
        return engine.makeItem(fields);
    }

    @Override
    public final String makeItem(FormatReportEngine engine, Employee emp) {
        return this.engine.makeItem(engine, emp);
    }

    @Override
    public final String makeContent(List<String> items) {
        return engine.makeContent(items);
    }
}
