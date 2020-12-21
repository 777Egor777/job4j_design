package ru.job4j.lsp.food.repeat;

import net.sf.saxon.trans.SymbolicName;

import java.io.Closeable;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 21.12.2020
 */
public interface Repeatable extends SimpleRepeatable, ForeverRepeatable, Closeable, AutoCloseable {
}
