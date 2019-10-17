package io.github.slemke.methodextractor.extraction;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;

import java.util.ArrayList;

public class ArgumentExtractor implements ExtractorStrategy {

    @Override
    public ArrayList<String> extract(String input) {
        ArrayList<String> arguments = new ArrayList<>();
        CompilationUnit compilationUnit = StaticJavaParser.parse(input);

        compilationUnit.findAll(MethodDeclaration.class)
                .stream()
                .forEach(f -> {
                    String methodArguments = "(";
                    NodeList<Parameter> parameters = f.getParameters();
                    for(int i = 0; i < parameters.size(); i++) {
                        methodArguments += parameters.get(i).toString();
                        if(i < parameters.size() - 1) {
                            methodArguments += ", ";
                        }
                    }
                    methodArguments += ")";

                    arguments.add(methodArguments);
                });
        return arguments;
    }
}
