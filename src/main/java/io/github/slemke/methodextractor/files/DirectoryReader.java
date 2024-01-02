package io.github.slemke.methodextractor.files;

import io.github.slemke.methodextractor.exceptions.FileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class DirectoryReader {

    public static ArrayList<String> read(String path) throws FileException {
        ArrayList<String> files = new ArrayList<>();

        try (Stream<Path> walk = Files.walk(Paths.get(path))) {
            walk.filter(Files::isRegularFile)
                .filter(f -> f.toString().toLowerCase().endsWith(".java"))
                .forEach(f -> files.add(f.toString()));
        } catch (IOException exception) {
            System.err.println(exception);
            throw new FileException("Unable to Java files from given directory", exception);
        }
        return files;
    }
}
