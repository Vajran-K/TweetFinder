package com.example;

public class ConfigLoader {

    public static String get(String key) {
        String value = System.getenv(key);

        if (value == null || value.isBlank()) {
            throw new RuntimeException(
                "Missing required environment variable: " + key
            );
        }

        return value;
    }
}
