package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MainZip {
    public static void main(String[] args) throws IOException {
        ArgZip arg = new ArgZip(args);
        if (!arg.valid()) {
            throw new IllegalArgumentException("Incorrect args");
        }
        List<Path> sources = Search.searchWithExclude(Paths.get(arg.directory()), arg.exclude());
        List<File> files = new ArrayList<>();
        sources.forEach(source -> files.add(source.toFile()));
        new Zip().packFiles(files, new File(arg.output()));
    }
}
