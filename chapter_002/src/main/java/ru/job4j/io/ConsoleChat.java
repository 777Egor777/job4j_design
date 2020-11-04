package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class ConsoleChat {
    private final String path;
    private final String botAnswersPath;
    private final List<String> botAnswers = new LinkedList<>();
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswersPath) {
        this.path = path;
        this.botAnswersPath = botAnswersPath;
    }

    private String getAnswer() {
        int rand = (int) (Math.random() * 100);
        if (rand < 0) {
            rand *= -1;
        }
        rand %= botAnswers.size();
        return botAnswers.get(rand);
    }

    private void readAnswersList() {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(botAnswersPath, Charset.forName("WINDOWS-1251"))
        )) {
            reader.lines().forEach(botAnswers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"))
        )) {
            for (String line : log) {
                writer.println(line);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        readAnswersList();
        List<String> log = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean work = true;
            boolean stopAnswer = false;
            while (work) {
                String line = reader.readLine();
                log.add(line);
                if (line.equals(OUT)) {
                    work = false;
                } else {
                    if (line.equals(STOP)) {
                        stopAnswer = true;
                    } else if (line.equals(CONTINUE)) {
                        stopAnswer = false;
                    }
                    if (!stopAnswer) {
                        String answer = getAnswer();
                        log.add(answer);
                        System.out.println(answer);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        printLog(log);
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("console_chat_log.txt",
                                 "console_chat_answers.txt");
        cc.run();
    }
}
