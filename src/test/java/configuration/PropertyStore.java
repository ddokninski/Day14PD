package configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum PropertyStore {

    PERFORM_ENVIRONMENT("perform_environment");

    private String propertyKey;
    private String value;

    public static final String CONFIG_YAML = "src/main/resources/config.yaml";
    public static final String ENVIRONMENT = "environments";
    public static final String BROWSER = "browser";
    public static final String PERFORM_BROWSER = "perform_browser";
    public static final String BROWSER_WEBELEMENT_TIMEOUT = "bowser.webelement.timeout";
    public static final String BROWSER_HEADLESS = "browser.headless";
    public static final String BROWSER_ATTACH_SCREENSHOT = "browser.attachscreenshot";
    private static Map<String, Object> propertiesMap = null;

    private PropertyStore(String key) {
        this.propertyKey = key;
        this.value = this.retrivePerformValue(key);
    }

    private String retrivePerformValue(String key) {
        return System.getProperty(key) != null ? System.getProperty(key) : getValueFromConfigFile(key);
    }

    private String getValueFromConfigFile(String key) {
        if (propertiesMap == null) {
            propertiesMap = loadConfigFile();
        }
        Object objFromFile = propertiesMap.get(key);
        return objFromFile != null ? Objects.toString(objFromFile) : null;
    }

    private Map<String, Object> loadConfigFile() {
        Map<String, Object> env = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper(new com.fasterxml.jackson.dataformat.yaml.YAMLFactory());
        File file = new File(CONFIG_YAML);
        try {
            env = mapper.readValue(file, Map.class);
            System.out.println(env);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return env;
    }

    public boolean isSpecified() {
        return StringUtils.isNotEmpty(this.value);
    }

    public String getValue() {
        return this.retrivePerformValue(this.propertyKey);
    }

    public int getIntValue() {
        return Integer.parseInt(this.retrivePerformValue(this.propertyKey));
    }

    public boolean getBooleanValue() {
        return this.isSpecified() && Boolean.parseBoolean(this.value);
    }
}
