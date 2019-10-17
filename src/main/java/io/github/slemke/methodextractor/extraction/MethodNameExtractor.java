package io.github.slemke.methodextractor.extraction;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.ArrayList;

public class MethodNameExtractor implements ExtractorStrategy {

    @Override
    public ArrayList<String> extract(String input) {
        ArrayList<String> names = new ArrayList<>();
        CompilationUnit compilationUnit = StaticJavaParser.parse(input);

        compilationUnit.findAll(MethodDeclaration.class)
                .stream()
                .forEach(f -> {
                    names.add(f.getNameAsString());
                });
        return names;
    }
}
