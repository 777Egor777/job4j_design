package ru.job4j.io;

import java.io.FileOutputStream;

public class Multiple {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("multiple.txt")) {
            String outputStr = "";
            for (int i = 1; i < 10; ++i) {
                for (int j = 1; j < 10; ++j) {
                    outputStr += i * j + " ";
                }
                outputStr += System.lineSeparator();
            }
            out.write(outputStr.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
