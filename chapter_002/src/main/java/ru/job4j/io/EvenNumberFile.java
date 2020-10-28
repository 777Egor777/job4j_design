package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream fin = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = fin.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                int number = Integer.parseInt(line);
                String result = number + " - ";
                if (isEven(number)) {
                    result += "even";
                } else {
                    result += "odd";
                }
                System.out.println(result);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
}
