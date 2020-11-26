package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Properties {
    private final Map<String, String> values = new HashMap<>();

    public Properties(String source) {
        readProperties(source);
    }

    private void readProperties(String source) {
        try(BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int pos = line.indexOf('=');
                String key = line.substring(0, pos);
                String value = line.substring(pos + 1);
                values.put(key, value);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getValue(String name) {
        if (!values.containsKey(name)) {
            throw new IllegalArgumentException("No such property in file");
        }
        return values.get(name);
    }
}
