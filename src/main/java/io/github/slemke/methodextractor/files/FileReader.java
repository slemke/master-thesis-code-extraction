package io.github.slemke.methodextractor.files;

import io.github.slemke.methodextractor.exceptions.FileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The FileReader class provides methods to read content from files.
 *
 * @author Sascha Lemke
 * @version 0.1
 */
public class FileReader {

    /**
     * Reads the text content of a file
     * @param path The path to the file
     * @return The text content of the given file
     */
    public static String read(String path) throws FileException {
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException exception) {
            throw new FileException("Unable to read text content from file: " + exception.getMessage(), exception);
        }
    }
}
