package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.lines().forEach(lines::add);
            for (String line : lines) {
                if (!line.equals("") && !line.startsWith("##")) {
                    String[] parts = line.split("=");
                    if (parts.length == 2) {
                        values.put(parts[0], parts[1]);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String value(String key) {
        String result = "";
        if (!values.containsKey(key)) {
            throw new NoSuchElementException("No value with such key");
        } else {
            result = values.get(key);
        }
        return result;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.lines().forEach(joiner::add);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return joiner.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
