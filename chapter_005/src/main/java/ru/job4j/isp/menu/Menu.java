package ru.job4j.isp.menu;

import java.util.List;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 20.12.2020
 */
public interface Menu extends Action, SetAction {
    String getName();
    List<Menu> getSubItems();
    void addSubItem(Menu menu);
    boolean isRoot();
}
