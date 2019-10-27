package io.github.slemke.methodextractor.extraction;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;

import java.util.ArrayList;

public class ArgumentExtractor implements Extractor {

    @Override
    public ArrayList<String> extract(String input) {
        ArrayList<String> argumentList = new ArrayList<>();
        try {
            CompilationUnit compilationUnit = StaticJavaParser.parse(input);
            compilationUnit.findAll(MethodDeclaration.class)
                    .forEach(f -> {
                        final String arguments = getParametersAsString(f.getParameters());
                        argumentList.add(arguments);
                    });
        } catch(Exception exception) {
            System.err.println("Parser error");
        }
        return argumentList;
    }

    private String getParametersAsString(NodeList<Parameter> parameters) {
        StringBuilder methodArguments = new StringBuilder("(");
        for(int i = 0; i < parameters.size(); i++) {
            methodArguments.append(parameters.get(i).toString());
            if(i < parameters.size() - 1) {
                methodArguments.append(", ");
            }
        }
        methodArguments.append(")");
        return methodArguments.toString();
    }
}
