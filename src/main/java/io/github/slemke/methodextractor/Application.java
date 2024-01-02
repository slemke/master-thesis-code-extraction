package io.github.slemke.methodextractor;

import io.github.slemke.methodextractor.exceptions.FileException;
import io.github.slemke.methodextractor.exceptions.UserInterfaceException;
import io.github.slemke.methodextractor.extraction.ArgumentExtractor;
import io.github.slemke.methodextractor.extraction.Extractor;
import io.github.slemke.methodextractor.extraction.MethodExtractor;
import io.github.slemke.methodextractor.extraction.MethodNameExtractor;
import io.github.slemke.methodextractor.files.DirectoryReader;
import io.github.slemke.methodextractor.files.FileReader;
import io.github.slemke.methodextractor.files.FileWriter;
import io.github.slemke.methodextractor.gui.UserInterface;
import io.github.slemke.methodextractor.normalization.Normalizer;

import java.util.ArrayList;

public class Application {

    private ArrayList<String> features = new ArrayList<>();
    private int index = 0;
    private UserInterface userInterface;

    public Application(String[] arguments) {
        final Extractor extractor = this.getExtractor(arguments[0]);
        try {
            getFeature(arguments[1], extractor);
            setupUserInterface();
        } catch (FileException exception) {
            System.err.println("Unable to read files: " + exception.getMessage());
        }
    }

    private void getFeature(String path, Extractor extractor) throws FileException {
        final ArrayList<String> files = DirectoryReader.read(path);
        for(String file : files) {
            final ArrayList<String> extractedFeatures = extractor.extract(FileReader.read(file));
            for(String feature : extractedFeatures) {
                if(!features.contains(feature)) {
                    features.add(feature);
                }
            }
        }
    }

    private Extractor getExtractor(String strategy) {
        if(strategy.equals("arguments")) {
            return new ArgumentExtractor();
        }
        if(strategy.equals("names")) {
            return new MethodNameExtractor();
        }
        return new MethodExtractor();
    }

    private void setupUserInterface() {
        try {
            this.userInterface = new UserInterface();
            this.userInterface.updateCounter(index, this.features.size());
            this.userInterface.updateFeature(features.get(0));


            this.userInterface.setCleanCodeAction(actionEvent -> classify("clean"));
            this.userInterface.setUncleanCodeAction(actionEvent -> classify("not_clean"));
            this.userInterface.setSkipAction(actionEvent -> skip());
        } catch (UserInterfaceException exception) {
            System.err.println("Unable to setup user interface: " + exception.getMessage());
        }
    }

    private String getFeature() {
        return this.features.get(this.index);
    }

    private void classify(String label) {
        String snippet = this.features.get(this.index);
        String normalized = Normalizer.normalize(snippet) + "\r\n";

        if(label.equals("clean")) {
            FileWriter.appendToFile(normalized, "src/main/resources/clean.txt");
        } else {
            FileWriter.appendToFile(normalized, "src/main/resources/not_clean.txt");
        }

        if(hasNext()) {
            next();
            this.userInterface.updateFeature(getFeature());
        }
    }

    private void skip() {
        if(hasNext()) {
            next();
            this.userInterface.updateFeature(getFeature());
        }
    }

    private boolean hasNext() {
        return this.index < this.features.size() - 1;
    }

    private void next() {
        this.index++;
        userInterface.updateCounter(index, this.features.size());
    }
}
