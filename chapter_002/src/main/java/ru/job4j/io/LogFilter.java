package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<String> lines = new ArrayList<>();
            reader.lines().forEach(lines::add);
            for (String line : lines) {
                String[] parts = line.split(" ");
                int number = Integer.parseInt(parts[parts.length - 2]);
                if (number == 404) {
                    result.add(line);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter writer = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String line : log) {
                writer.write(line + System.lineSeparator());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}
