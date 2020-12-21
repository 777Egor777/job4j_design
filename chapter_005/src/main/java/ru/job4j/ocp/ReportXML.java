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
public class ReportXML implements ReportEngine, FieldMaker {
    private final FormatReport report;

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
        report = new FormatReport(store);
        init();
    }

    private void init() {
        report.setFieldMaker(this);
        report.setPartTab(" ");
        report.setFieldCountOfPartTabs(4);
        report.setItemCountOfPartTabs(2);
        report.setItemPrefix("<employee>" + System.lineSeparator());
        report.setFieldSeparator(System.lineSeparator());
        report.setItemSuffix(System.lineSeparator() + "  </employee>");
        report.setContentPrefix("<?xml version=\"1.0\"?>"
                              + System.lineSeparator()
                              + "<employees>"
                              + System.lineSeparator());
        report.setContentSuffix(System.lineSeparator() + "</employees>");
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
        return report.generate(filter);
    }

    @Override
    public final String makeField(String key, String value) {
        return report.getTab().tab(report.getFieldCountOfPartTabs())
                + report.getFieldPrefix()
                + String.format("<%s>%s</%s>", key, value, key)
                + report.getFieldSuffix();
    }
}
