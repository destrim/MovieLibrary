package com.destrim.util;

public class VariousUtils {

    public static String[] splitIgnoreInQuotes(String string, char splitter) {
        return string.split(splitter + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
    }
}
