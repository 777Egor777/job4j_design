package ru.job4j.isp.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 20.12.2020
 */
public final class MenuItem implements Menu {
    private final String name;
    private final boolean isRoot;
    private final List<Menu> subItems = new ArrayList<>();
    private Action action;
    private final MenuToString menuToString = new MenuHelper();

    public MenuItem(String name) {
        this.name = name;
        this.isRoot = false;
    }

    public MenuItem(String name, boolean isRoot) {
        this.name = name;
        this.isRoot = isRoot;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final List<Menu> getSubItems() {
        return subItems;
    }

    @Override
    public final void addSubItem(Menu menu) {
        subItems.add(menu);
    }

    @Override
    public final boolean isRoot() {
        return isRoot;
    }

    @Override
    public final String toString() {
        return menuToString.menuToString(this);
    }

    @Override
    public final void doAction() {
        if (action != null) {
            action.doAction();
        }
    }

    @Override
    public final void setAction(Action action) {
        this.action = action;
    }
}
