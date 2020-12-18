package ru.job4j.ocp;

import ru.job4j.ocp.util.Tab;
import ru.job4j.rsp.Employee;
import ru.job4j.rsp.ReportEngine;
import ru.job4j.rsp.Store;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class helps to
 * generate report
 * in XML format.
 *
 * Employees filtered
 * by predicate.
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 17.12.2020
 */
public class ReportXML implements FormatReportEngine {
    private final FormatReportEngine engine;

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
    public ReportXML(Store store) {
        this.store = store;
        engine = new FormatReport(store);
        init();
    }

    private void init() {
        engine.setPartTab(" ");
        engine.setFieldCountOfPartTabs(4);
        engine.setItemCountOfPartTabs(2);
        engine.setItemPrefix("<employee>" + System.lineSeparator());
        engine.setFieldSeparator(System.lineSeparator());
        engine.setItemSuffix(System.lineSeparator() + "  </employee>");
        engine.setContentPrefix("<?xml version=\"1.0\"?>"
                              + System.lineSeparator()
                              + "<employees>"
                              + System.lineSeparator());
        engine.setContentSuffix(System.lineSeparator() + "</employees>");
    }

    /**
     * Method generate report
     * in XML format.
     *
     * Employees filtered
     * by predicate.
     *
     * @param filter - predicate.
     * @return report in XML
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
        return getTab().tab(getFieldCountOfPartTabs())
                + getFieldPrefix()
                + String.format("<%s>%s</%s>", key, value, key)
                + getFieldSuffix();
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
