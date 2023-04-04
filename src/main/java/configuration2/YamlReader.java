package configuration2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class YamlReader {

    public Config config;

    public Config getConfig() {
        return config;
    }

    public YamlReader() {
        try {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            config = objectMapper.readValue(new File("src/main/resources/config1.yaml"), Config.class);
            System.out.println(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
