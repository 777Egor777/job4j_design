package ru.job4j.template;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GeneratorTest {

    @Test
    public void whenCorrectWork() {
        String template = "Hello, my name is ${name}. I am ${age} years old.";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Egor");
        map.put("age", "25");
        Generator generator = new SimpleGenerator();
        String result = generator.produce(template, map);
        String expected = "Hello, my name is Egor. I am 25 years old.";
        assertThat(result, is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenIncorrectTemplateArgs() {
        String template = "Hello, my name is ${name}. I am ${age} years old. I am from ${town}.";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Egor");
        map.put("age", "25");
        Generator generator = new SimpleGenerator();
        String result = generator.produce(template, map);
        String expected = "Hello, my name is Egor. I am 25 years old";
        assertThat(result, is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenIncorrectMapArgs() {
        String template = "Hello, my name is ${name}. I am ${age} years old.";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Egor");
        map.put("age", "25");
        map.put("town", "Saratov");
        Generator generator = new SimpleGenerator();
        String result = generator.produce(template, map);
        String expected = "Hello, my name is Egor. I am 25 years old.";
        assertThat(result, is(expected));
    }
}