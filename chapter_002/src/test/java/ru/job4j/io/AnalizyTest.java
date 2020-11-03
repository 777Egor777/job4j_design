package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenOnePeriod() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 13:42:01");
            out.println("400 13:52:13");
            out.println("300 13:55:27");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(joiner::add);
        }
        assertThat(joiner.toString(), is("13:52:13;13:55:27"));
    }

    @Test
    public void whenFourPeriods() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 13:42:01");
            out.println("400 13:52:13");
            out.println("300 13:55:27");
            out.println("500 16:42:19");
            out.println("400 17:42:19");
            out.println("200 19:11:44");
            out.println("200 19:11:45");
            out.println("500 19:13:18");
            out.println("200 19:22:11");
            out.println("300 21:22:11");
            out.println("400 23:22:12");
            out.println("500 23:30:33");
            out.println("300 23:40:11");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(joiner::add);
        }
        StringJoiner result = new StringJoiner(System.lineSeparator());
        result.add("13:52:13;13:55:27");
        result.add("16:42:19;19:11:44");
        result.add("19:13:18;19:22:11");
        result.add("23:22:12;23:40:11");
        assertThat(joiner.toString(), is(result.toString()));
    }

    @Test
    public void whenTwoPeriods() {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/when_two_periods_source.txt",
                            "./data/when_two_periods_target.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader("./data/when_two_periods_target.txt"))) {
            assertThat(reader.readLine() + System.lineSeparator() + reader.readLine(),
                       is("10:57:01;10:59:01" + System.lineSeparator() + "11:01:02;11:02:02"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}