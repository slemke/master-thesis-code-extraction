package io.github.slemke.methodextractor.normalization;

/**
 * The Normalizer class provides methods to normalize methods from an AST.
 * @author Sascha Lemke
 * @version 0.1
 */
public class Normalizer {

    /**
     * Normalizes a string containing a method declaration.
     *
     * - Removes all tabs
     * - Removes all newline character and replaces them with a space
     * - Reduces repeating spaces to one space
     *
     * @param method The method string
     * @return The normalized declaration
     */
    public static String normalize(String method) {
        method = method.replace("\t", "");
        method = method.replace("\n\r", " ");
        method = method.replace("\r\n", " ");
        method = method.replace("\n", " ");
        method = method.replace("\r", " ");
        method = method.replaceAll(" {2,}", " ");
        return method;
    }
}
