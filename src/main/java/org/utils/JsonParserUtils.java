package org.utils;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;

public class JsonParserUtils {
    public static <T> T parseToObject(String pathToTestFile, Class<T> classOfT) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(pathToTestFile)) {
            return gson.fromJson(reader, classOfT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
