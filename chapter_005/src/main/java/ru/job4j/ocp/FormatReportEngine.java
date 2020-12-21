package ru.job4j.ocp;

import ru.job4j.rsp.ReportEngine;

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
public interface FormatReportEngine extends ReportEngine, FieldMaker, ItemMaker {
}
