package io.github.slemke.methodextractor.normalization;

/**
 * The Normalizer class provides methods to normalize features from an AST.
 * @author Sascha Lemke
 * @version 0.1
 */
public class Normalizer {

    /**
     * Normalizes a string containing a feature.
     *
     * - Removes all tabs
     * - Removes all newline character and replaces them with a space
     * - Reduces repeating spaces to one space
     *
     * @param feature The feature string
     * @return The normalized feature
     */
    public static String normalize(String feature) {
        feature = feature.replace("\t", "");
        feature = feature.replace("\n\r", " ");
        feature = feature.replace("\r\n", " ");
        feature = feature.replace("\n", " ");
        feature = feature.replace("\r", " ");
        feature = feature.replaceAll(" {2,}", " ");
        return feature;
    }
}
