package configuration2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;

public class test {

    public static void main(String[] args) {

//        System.out.println(yamlReader.getConfig());

//        Map<String, Object> env = new HashMap<>();
//        ObjectMapper mapper = new ObjectMapper(new com.fasterxml.jackson.dataformat.yaml.YAMLFactory());
//        File file = new File("src/main/resources/config.yaml");
//        try {
//            env = mapper.readValue(file, Map.class);
//            System.out.println(env);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Map<String, Object> environment = (Map<String, Object>) env.get("environment");
//        environment.get("test_int");

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        File file = new File("src/main/resources/config1.yaml");
        try {
//            Map<String, Object> config = mapper.readValue(file, Map.class);
            Config config = mapper.readValue(file, Config.class);
//            System.out.println(config.getBrowser().getBrowserProperties().get("browser_name"));
            System.out.println(config.getEnvironment().getEnvironments().get(0));

//            Map<String, Object> browserProperties = (Map<String, Object>) config.get("environment");
//            System.out.println(browserProperties);
//            for (Map.Entry<String, Object> entry : browserProperties.entrySet()) {
//                String key = entry.getKey();
//                Object value = entry.getValue();
//                System.setProperty(key, value.toString());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
