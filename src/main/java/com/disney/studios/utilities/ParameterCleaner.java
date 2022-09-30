package com.disney.studios.utilities;

import java.util.HashMap;
import java.util.Map;

public class ParameterCleaner {

    public static String clean(String parameter) {
        return parameter.replaceAll("\"", "");
    }
}
