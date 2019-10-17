package io.github.slemke.methodextractor;

import com.github.javaparser.ast.body.MethodDeclaration;
import io.github.slemke.methodextractor.exceptions.FileException;
import io.github.slemke.methodextractor.exceptions.GUIException;
import io.github.slemke.methodextractor.extraction.Extractor;
import io.github.slemke.methodextractor.files.DirectoryReader;
import io.github.slemke.methodextractor.files.FileReader;
import io.github.slemke.methodextractor.gui.GUI;
import io.github.slemke.methodextractor.normalization.Normalizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/**
 * This application collects all the moves from a directory so the can be manually classified as clean or not clean.
 * This speeds up the dataset building in a semi automatic way.
 */
public class Application {

    private ArrayList<MethodDeclaration> methods = new ArrayList<>();
    private int index = 0;
    private GUI gui;

    /**
     * Starts the application and reads all the Java files from arguments path.
     *
     * @param arguments The CLI arguments
     */
    public Application(String[] arguments) {
        try {
            getMethods(arguments[0]);
            setupGUI();
        } catch (FileException e) {
            System.err.println("Unable to read files: " + e.getMessage());
        }
    }

    /**
     * Gets the content of all Java files from the given path.
     * @param path The root path
     */
    private void getMethods(String path) throws FileException {
        final ArrayList<String> files = DirectoryReader.read(path);
        for(String file : files) {
            methods.addAll(Extractor.extract(FileReader.read(file)));
        }
    }

    /**
     * Sets up the applications GUI.
     */
    private void setupGUI() {
        try {
            this.gui = new GUI();
            this.gui.updateCounter(index, this.methods.size());
            this.gui.updateCode(methods.get(0).toString());
            this.gui.setCleanCodeAction(actionEvent -> classify(true));
            this.gui.setUncleanCodeAction(actionEvent -> classify(false));
            this.gui.setSkipAction(actionEvent -> skip());
        } catch (GUIException e) {
            System.err.println("Unable to setup GUI: " + e.getMessage());
        }
    }

    /**
     * Returns the currently selected method from the internal list of methods.
     * @return The currently selected method as a string
     */
    public String getCurrentMethod() {
        return this.methods.get(this.index).toString();
    }

    /**
     * Classifies the method and saves it to disk.
     * @param clean True, if the code is clean. Otherwise false.
     */
    public void classify(boolean clean) {
        MethodDeclaration method = this.methods.get(this.index);
        String normalized = normalizeMethod(method);

        if(clean) {
            saveMethod(normalized, "../../dataset/clean.txt");
        } else {
            saveMethod(normalized, "../../dataset/notClean.txt");
        }

        if(hasNext()) {
            next();
            this.gui.updateCode(getCurrentMethod());
        }
    }

    /**
     * Normalizes a method from the AST to save it in a text file.
     * @param method The method from the AST
     * @return The normalized method
     */
    private String normalizeMethod(MethodDeclaration method) {
        method.removeJavaDocComment();
        return Normalizer.normalize(method.toString()) + "\r\n";
    }

    /**
     * Saves a method in the given file.
     * @param method The method
     * @param filePath The path to the file
     */
    private void saveMethod(String method, String filePath) {
        Path path = Paths.get(filePath);
        try {
            Files.write(path, method.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Unable to save method: " + e.getMessage());
        }
    }

    /**
     * Skip the currently selected method.
     */
    public void skip() {
        if(hasNext()) {
            next();
            this.gui.updateCode(getCurrentMethod());
        }
    }

    /**
     * Check if there are more methods in the list.
     * @return True, if there are more methods. Otherwise false.
     */
    public boolean hasNext() {
        return this.index < this.methods.size() - 1;
    }

    /**
     * Selects the next method in the list.
     */
    public void next() {
        this.index++;
        gui.updateCounter(index, this.methods.size());
    }
}
