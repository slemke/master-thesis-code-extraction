package io.github.slemke.methodextractor.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWriter {

    public static void appendToFile(String snippet, String filename) {
        Path path = Paths.get(filename);
        try {
            Files.write(path, snippet.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Unable to save snippet: " + e.getMessage());
        }
    }
}
