package ru.job4j.io.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Shell {
    private final Stack<String> stack = new Stack<>();
    public void cd(String path) {
        String[] parts = path.split("/");
        for (String part : parts) {
            if (part.equals("..")) {
                stack.pop();
            } else {
                stack.push(part);
            }
        }
    }

    public String pwd() {
        String result = "";
        List<String> list = new ArrayList<>(stack);
        for (String part : list) {
            result += "/" + part;
        }
        if (result.equals("")) {
            result = "/";
        }
        return result;
    }
}
