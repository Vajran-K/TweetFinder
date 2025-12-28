package com.example;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static Properties props = new Properties();

    static {
        try (InputStream input =
             ConfigLoader.class
                 .getClassLoader()
                 .getResourceAsStream("application.properties")) {

            if (input == null) {
                throw new RuntimeException("application.properties not found");
            }
            props.load(input);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
