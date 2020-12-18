package ru.job4j.ocp.util;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TabFactoryTest {

    @Test
    public void tab() {
        Tab tab = new TabFactory(" ", 5);
        String result = tab.tab(3);
        String expected = "   ";
        assertThat(result, is(expected));
    }
}