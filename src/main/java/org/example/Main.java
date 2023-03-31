package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper(new com.fasterxml.jackson.dataformat.yaml.YAMLFactory());
        File file = new File("src/main/resources/config.yaml");
        Map<String, Object> obj = mapper.readValue(file, Map.class);
        String defaultEnvironment = (String) obj.get("perform_environment");
        System.out.println("Default environment: " + defaultEnvironment);
    }
}
