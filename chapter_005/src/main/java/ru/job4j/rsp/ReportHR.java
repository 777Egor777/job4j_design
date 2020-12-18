package ru.job4j.rsp;

import ru.job4j.ocp.FormatReport;
import ru.job4j.ocp.FormatReportEngine;
import ru.job4j.ocp.util.Tab;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Method generate report
 * in format that
 * HR department
 * of the company asks.
 *
 * Employees filtered
 * by predicate.
 *
 * Employees sorted by
 * desc of salary.
 *
 * Dates when employee
 * hired and fired
 * removed from the
 * report.
 *
 * @author Geraskin Egor
 * @version 2.0
 * @since 18.12.2020
 */
public class ReportHR implements FormatReportEngine {
    private final FormatReportEngine engine;

    /**
     * Store, from which we
     * extract info about
     * employees.
     */
    private final Store store;

    /**
     * Comparator for {@code Employee}
     * objects that we will use
     * to sort employees
     * desc by salary.
     */
    private final Comparator<Employee> employeeCmpDescBySalary = (o1, o2) -> Double.compare(o1.getSalary(), o2.getSalary()) * -1;


    /**
     * Constructor.
     * @param store - init value of the
     *                {@code store} field.
     */
    public ReportHR(Store store) {
        this.store = store;
        engine = new FormatReport(store);
        init();
    }

    private void init() {
        engine.setContentPrefix("Name;Salary;" + System.lineSeparator());
        engine.setItemSuffix(";");
        engine.setItemSeparator(System.lineSeparator());
        engine.setFieldSeparator(";");
    }

    /**
     * Method generate report
     * in format that
     * HR department
     * of the company asks.
     *
     * Employees filtered
     * by predicate.
     *
     * Employees sorted by
     * desc of salary.
     *
     * In this report there
     * are no dates when
     * employee hired
     * and fired.
     *
     * @param filter - predicate.
     * @return - report in "HR"
     *           format
     */
    @Override
    public final String generate(Predicate<Employee> filter) {
        return makeContent(store.findBy(filter).stream().sorted(employeeCmpDescBySalary).map(emp -> makeItem(this, emp))
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
        return engine.makeField(key, value);
    }

    @Override
    public final String makeItem(List<String> fields) {
        return engine.makeItem(fields);
    }

    @Override
    public final String makeItem(FormatReportEngine engine, Employee emp) {
        List<String> fields = new ArrayList<>();
        fields.add(makeField("name", emp.getName()));
        fields.add(makeField("salary", "" + emp.getSalary()));
        return makeItem(fields);
    }

    @Override
    public final String makeContent(List<String> items) {
        return engine.makeContent(items);
    }
}
