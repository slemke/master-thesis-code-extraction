package io.github.slemke.methodextractor.extraction;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.ArrayList;

/**
 * The Extractor class provides methods to extract specific parts of Java code.
 *
 * @author Sascha Lemke
 * @version 0.1
 */
public class Extractor {

    /**
     * Extracts all methods from a Java input string.
     *
     * @param input A String containing Java code
     * @return A List of Java method declarations
     */
    public static ArrayList<MethodDeclaration> extract(String input) {
        ArrayList<MethodDeclaration> methods = new ArrayList<>();

        CompilationUnit compilationUnit = StaticJavaParser.parse(input);

        compilationUnit.findAll(MethodDeclaration.class)
                .stream()
                .peek(f -> {
                    f.setAnnotations(new NodeList<>());
                })
                .forEach(methods::add);
        return methods;
    }
}
