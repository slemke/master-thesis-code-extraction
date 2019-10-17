package io.github.slemke.methodextractor.extraction;

import java.util.ArrayList;

public interface ExtractorStrategy {

    ArrayList<String> extract(String input);
}
