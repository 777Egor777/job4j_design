package ru.job4j.isp.menu;

import java.util.List;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 20.12.2020
 */
public final class MenuRoot implements Menu {
    private final Menu menu = new MenuItem("Root", true);

    @Override
    public final void doAction() {
        throw new UnsupportedOperationException("Menu root doesn't support any action.");
    }

    @Override
    public final String getName() {
        return menu.getName();
    }

    @Override
    public final List<Menu> getSubItems() {
        return menu.getSubItems();
    }

    @Override
    public final void addSubItem(Menu menu) {
        this.menu.addSubItem(menu);
    }

    @Override
    public final boolean isRoot() {
        return menu.isRoot();
    }

    @Override
    public final void setAction(Action action) {
        throw new UnsupportedOperationException("Menu root doesn't support any action.");
    }

    @Override
    public final String toString() {
        return menu.toString();
    }
}
