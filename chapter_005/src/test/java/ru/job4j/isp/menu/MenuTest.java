package ru.job4j.isp.menu;

import org.junit.Test;

import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MenuTest {

    @Test
    public void getNameWhenRoot() {
        Menu menu = new MenuRoot();
        assertThat(menu.getName(), is("Root"));
    }

    @Test
    public void getNameWhenNotRoot() {
        Menu menu = new MenuItem("Task");
        assertThat(menu.getName(), is("Task"));
    }

    @Test
    public void getSubItemsWhenOneSubItem() {
        Menu menu = new MenuItem("Menu");
        Menu sub = new MenuItem("sub");
        menu.addSubItem(sub);
        assertThat(menu.getSubItems().get(0).getName(), is("sub"));
    }

    @Test
    public void getSubItemsWhenManySubItems() {
        Menu menu = new MenuItem("Menu");
        Menu sub1 = new MenuItem("sub1");
        Menu sub2 = new MenuItem("sub2");
        Menu sub3 = new MenuItem("sub3");
        menu.addSubItem(sub1);
        menu.addSubItem(sub2);
        menu.addSubItem(sub3);
        assertThat(menu.getSubItems().get(1).getName(), is("sub2"));
    }

    @Test
    public void whenGetFullMenuFromToStringMethod() {
        Menu menu = new MenuRoot();
        Menu task1 = new MenuItem("Task 1.");
        Menu task11 = new MenuItem("Task 1.1.");
        Menu task111 = new MenuItem("Task 1.1.1.");
        Menu task112 = new MenuItem("Task 1.1.2.");
        Menu task12 = new MenuItem("Task 1.2.");
        Menu task2 = new MenuItem("Task 2.");
        menu.addSubItem(task1);
        menu.addSubItem(task2);
        task1.addSubItem(task11);
        task1.addSubItem(task12);
        task11.addSubItem(task111);
        task11.addSubItem(task112);
        String result = menu.toString();
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        joiner.add("Task 1.");
        joiner.add("----Task 1.1.");
        joiner.add("--------Task 1.1.1.");
        joiner.add("--------Task 1.1.2.");
        joiner.add("----Task 1.2.");
        joiner.add("Task 2.");
        String expected = joiner.toString();
        assertThat(result, is(expected));
    }

    @Test
    public void whenRoot() {
        Menu menu = new MenuRoot();
        assertTrue(menu.isRoot());
    }

    @Test
    public void whenNotRoot() {
        Menu menu = new MenuItem("Task");
        assertFalse(menu.isRoot());
    }
}