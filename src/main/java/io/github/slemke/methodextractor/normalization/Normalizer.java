package io.github.slemke.methodextractor.normalization;

public class Normalizer {

    public static String normalize(String feature) {
        feature = feature.replace("\t", "");
        feature = feature.replace("\n\r", " ");
        feature = feature.replace("\r\n", " ");
        feature = feature.replace("\n", " ");
        feature = feature.replace("\r", " ");
        feature = feature.replaceAll("\\(", " ( ");
        feature = feature.replaceAll("\\)", " ) ");
        feature = feature.replaceAll("<\\?", " <? ");
        feature = feature.replaceAll("<", " < ");
        feature = feature.replaceAll(">", " > ");
        feature = feature.replaceAll("\\{", " { ");
        feature = feature.replaceAll("}", " } ");
        feature = feature.replaceAll("\"", " \" ");
        feature = feature.replaceAll(",", " , ");
        feature = feature.replaceAll(";", " ; ");
        feature = feature.replaceAll(" {2,}", " ");
        return feature;
    }
}
