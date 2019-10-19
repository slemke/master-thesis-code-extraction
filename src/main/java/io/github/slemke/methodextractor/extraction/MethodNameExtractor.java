package io.github.slemke.methodextractor.extraction;

import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.ArrayList;

public class MethodNameExtractor implements Extractor {

    @Override
    public ArrayList<String> extract(String input) {
        ArrayList<String> names = new ArrayList<>();
        try {
            CompilationUnit compilationUnit = StaticJavaParser.parse(input);
            compilationUnit.findAll(MethodDeclaration.class)
                    .forEach(f -> names.add(f.getNameAsString()));
        } catch(Exception exception) {
            System.err.println("Parser error");
        }
        return names;
    }
}
