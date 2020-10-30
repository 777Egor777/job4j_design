package ru.job4j.io;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> periods = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            List<String> lines = new LinkedList<>();
            reader.lines().forEach(lines::add);
            String startOfPeriod = null;
            for (String line : lines) {
                String[] parts = line.split(" ");
                int type = Integer.parseInt(parts[0]);
                String date = parts[1];
                if ((type == 400 || type == 500)) {
                    if (startOfPeriod == null) {
                        startOfPeriod = date;
                    }
                } else {
                    if (startOfPeriod != null) {
                        periods.add(startOfPeriod + ";" + date);
                        startOfPeriod = null;
                    }
                }
            }
            if (startOfPeriod != null) {
                periods.add(startOfPeriod + ";");
            }
            for (String period : periods) {
                writer.println(period);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter writer = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("unavailable.csv")
                )
        )) {
            writer.println("15:01:30;15:02:32");
            writer.println("15:10:30;23:12:32");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
