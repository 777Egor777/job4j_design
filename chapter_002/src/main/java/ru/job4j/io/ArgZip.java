package ru.job4j.io;

import java.io.File;

public class ArgZip {
    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        boolean result = false;
        if (args.length >= 6
            && args[0].equals("-d")
            && args[2].equals("-e")
            && args[4].equals("-o")
            && args[5].endsWith(".zip")) {
            File file = new File(args[1]);
            if (file.exists() && file.isDirectory()) {
                result = true;
            }
        }
        return result;
    }

    public String directory() {
        return args[1];
    }

    public String exclude() {
        return args[3];
    }

    public String output() {
        return args[5];
    }
}
