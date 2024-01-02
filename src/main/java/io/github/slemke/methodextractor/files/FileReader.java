package io.github.slemke.methodextractor.files;

import io.github.slemke.methodextractor.exceptions.FileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {

    public static String read(String path) throws FileException {
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException exception) {
            throw new FileException("Unable to read text content from file: " + exception.getMessage(), exception);
        }
    }
}
