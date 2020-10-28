package ru.job4j.finaltask;

import ru.job4j.finaltask.util.graph.Graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StartUI {
    public static void main(String[] args) throws IOException {
        StartUI ui = new StartUI();
        List<String> lines = ui.readLines();
        Graph graph = ui.makeGraph(lines);
        List<List<String>> groups = ui.makeGroups(graph.getSources(), lines);
        ui.printGroups(groups);
    }

    public List<String> readLines() throws IOException {
        List<String> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.csv")));
        String line;
        while ((line = reader.readLine()) != null) {
            result.add(line);
        }
        return result;
    }

    public Graph makeGraph(List<String> lines) {
        Graph graph = new Graph(lines.size());
        Map<String, Integer> partStrToIndex = new HashMap<>();
        for (int index = 0; index < lines.size(); ++index) {
            String line = lines.get(index);
            for (String part : line.split(";")) {
                if (!part.equals("")) {
                    if (partStrToIndex.containsKey(part)) {
                        graph.addEdge(index, partStrToIndex.get(part));
                    }
                    partStrToIndex.put(part, index);
                }
            }
        }
        return graph;
    }

    public List<List<String>> makeGroups(int[] sources, List<String> lines) {
        Map<Integer, List<String>> groupsMap = new HashMap<>();
        for (int index = 0; index < lines.size(); ++index) {
            List<String> current = groupsMap.get(sources[index]);
            current.add(lines.get(index));
        }
        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : groupsMap.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    public void printGroups(List<List<String>> groups) {
        for (int index = 0; index < groups.size(); ++index) {
            System.out.println("Группа " + (index + 1));
            for (String line : groups.get(index)) {
                System.out.println(line);
            }
        }
    }
}
