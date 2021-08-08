package com.power.springbootapi.helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class PostcodeInfoTestDataHelper {
    public static String convertJsonToString(String fileName) {
        InputStream input = PostcodeInfoTestDataHelper.class.getClassLoader()
                .getResourceAsStream(fileName);
        assert input != null;
        InputStreamReader reader = new InputStreamReader(input);
        return new BufferedReader(reader).lines()
                .collect(Collectors.joining(""));
    }
}
