package io.github.slemke.methodextractor.extraction;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.ArrayList;

/**
 * The Extractor class provides methods to extract specific parts of Java code.
 *
 * @author Sascha Lemke
 * @version 0.1
 */
public class MethodExtractor implements Extractor {

    /**
     * Extracts all methods from a Java input string.
     *
     * @param input A String containing Java code
     * @return A List of Java method declarations
     */
    @Override
    public ArrayList<String> extract(String input) {
        ArrayList<String> methods = new ArrayList<>();
        try {
            StaticJavaParser.getConfiguration().setAttributeComments(false);
            CompilationUnit compilationUnit = StaticJavaParser.parse(input);
            compilationUnit.findAll(MethodDeclaration.class)
                .stream()
                .filter(f -> !f.isAbstract())
                .filter(CallableDeclaration::isCallableDeclaration)
                .filter(f -> !f.isClassOrInterfaceDeclaration())
                .peek(f -> {
                    f.setAnnotations(new NodeList<>());
                })
                .forEach(f -> {
                    f.removeComment();
                    f.removeJavaDocComment();

                    if(f.isCallableDeclaration()) {
                        methods.add(f.toString());
                    }
                });
        } catch(Exception exception) {
            System.err.println("Parser error");
        }
        return methods;
    }
}
