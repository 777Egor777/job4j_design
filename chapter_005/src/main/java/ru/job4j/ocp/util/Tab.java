package ru.job4j.ocp.util;

/**
 * Interface that helps
 * us to make classes,
 * that generate tubs
 * for using them in
 * text.
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 17.12.2020
 */
public interface Tab {
    /**
     * Method return final
     * tab, that included
     * countOfTabParts
     * similar tab parts.
     *
     * @param countOfTabParts - count of tab
     *                          parts in the
     *                          whole tab value.
     * @return - final tab value
     */
    String tab(int countOfTabParts);
}
