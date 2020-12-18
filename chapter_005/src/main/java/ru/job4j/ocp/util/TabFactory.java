package ru.job4j.ocp.util;

/**
 * Class that helps us
 * to make tabs in the text.
 *
 * Using tab value and
 * count of tab values,
 * it generates whole
 * final tab.
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 17.12.2020
 */
public class TabFactory implements Tab {
    /**
     * Single part of tab
     * value.
     */
    private final String tabPart;

    /**
     * Capacity of precalculated
     * array of final tabs.
     */
    private final int capacity;

    /**
     * Array of precalculated
     * values of final tabs.
     * It's size - {@code capacity}
     * field.
     */
    private final String[] tabs;

    /**
     * Constructor.
     * Init values of
     * {@code tabPart} and
     * {@code capacity}
     * fields.
     *
     * @param tabPart - init value of
     *                  {@code tabPart}
     *                  field.
     * @param capacity - init value of
     *                   {@code capacity}
     *                   field.
     */
    public TabFactory(String tabPart, int capacity) {
        this.tabPart = tabPart;
        this.capacity = capacity;
        tabs = new String[capacity];
        precalculate();
    }

    /**
     * This method precalculate
     * values of final tabs.
     */
    private void precalculate() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < capacity; ++i) {
            tabs[i] = builder.toString();
            builder.append(tabPart);
        }
    }

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
    @Override
    public String tab(int countOfTabParts) {
        return tabs[countOfTabParts];
    }
}
