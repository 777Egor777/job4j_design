package ru.job4j.isp.menu;

import ru.job4j.ocp.util.Tab;
import ru.job4j.ocp.util.TabFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Geraskin Egor
 * @version 1.0
 * @since 20.12.2020
 */
public final class MenuHelper implements MenuToString {
    private final static String TAB_PART = "----";
    private final static int TAB_FACTORY_CAPACITY = 100;
    private final static Tab TAB = new TabFactory(TAB_PART, 100);

    @Override
    public final String menuToString(Menu menu) {
        int depth = 1;
        if (menu.isRoot()) {
            depth = 0;
        }
        return dfs(depth, menu).stream().collect(Collectors.joining(System.lineSeparator()));
    }

    private List<String> dfs(int depth, Menu menu) {
        List<String> result = new ArrayList<>();
        if (!menu.isRoot()) {
            result.add(TAB.tab(depth - 1)
                    + menu.getName());
        }
        for (Menu sub : menu.getSubItems()) {
            result.addAll(dfs(depth + 1, sub));
        }
        return result;
    }
}
