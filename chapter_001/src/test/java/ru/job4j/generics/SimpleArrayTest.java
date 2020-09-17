package ru.job4j.generics;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void whenAddOneElement() {
        SimpleArray<String> smpAr = new SimpleArray<>(10);
        smpAr.add("Egor");
        assertThat(smpAr.get(0), is("Egor"));
    }

    @Test
    public void whenAddSomeElements() {
        SimpleArray<String> smpAr = new SimpleArray<>(10);
        smpAr.add("Egor");
        smpAr.add("Vanya");
        smpAr.add("Petr");
        smpAr.add("Viktor");
        assertThat(smpAr.get(2), is("Petr"));
    }

    @Test
    public void whenSetFirstElement() {
        SimpleArray<String> smpAr = new SimpleArray<>(10);
        smpAr.add("Egor");
        smpAr.add("Vanya");
        smpAr.add("Petr");
        smpAr.add("Viktor");
        smpAr.set(0, "Efim");
        assertThat(smpAr.get(0), is("Efim"));
    }

    @Test
    public void whenSetLastElement() {
        SimpleArray<String> smpAr = new SimpleArray<>(10);
        smpAr.add("Egor");
        smpAr.add("Vanya");
        smpAr.add("Petr");
        smpAr.add("Viktor");
        smpAr.set(3, "Alex");
        assertThat(smpAr.get(3), is("Alex"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetAndIndexOutOfBounds() {
        SimpleArray<String> smpAr = new SimpleArray<>(10);
        smpAr.add("Egor");
        smpAr.add("Vanya");
        smpAr.add("Petr");
        smpAr.add("Viktor");
        smpAr.set(5, "Alex");
    }

    @Test
    public void whenRemoveMiddleElement() {
        SimpleArray<String> smpAr = new SimpleArray<>(10);
        smpAr.add("Egor");
        smpAr.add("Vanya");
        smpAr.add("Petr");
        smpAr.add("Viktor");
        smpAr.remove(1);
        assertThat(smpAr.get(1), is("Petr"));
        assertThat(smpAr.get(2), is("Viktor"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveAndIndexOutOfBounds() {
        SimpleArray<String> smpAr = new SimpleArray<>(10);
        smpAr.add("Egor");
        smpAr.add("Vanya");
        smpAr.add("Petr");
        smpAr.add("Viktor");
        smpAr.remove(4);
    }

    @Test
    public void iterator() {
        SimpleArray<String> smpAr = new SimpleArray<>(10);
        smpAr.add("Egor");
        smpAr.add("Vanya");
        smpAr.add("Petr");
        smpAr.add("Viktor");
        Iterator<String> it = smpAr.iterator();
        assertThat(it.next(), is("Egor"));
        assertThat(it.next(), is("Vanya"));
        assertThat(it.next(), is("Petr"));
        assertThat(it.next(), is("Viktor"));
    }
}