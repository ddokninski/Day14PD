package configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class EnvironmentProperty {

    private final String APP_ENV;

    private static Logger logger = LoggerFactory.getLogger(EnvironmentProperty.class);

    private final BrowserEnvironment BROWSER_ENV;

    public static EnvironmentProperty getInstance() {
        return EnvironmentPropertySingleton.INSTANCE;
    }

    private EnvironmentProperty() {
        this.APP_ENV = initAppEnv();
        this.BROWSER_ENV = new BrowserEnvironment();
        this.initEnv();
    }

    private static String initAppEnv() {
        return PropertyStore.PERFORM_ENVIRONMENT.isSpecified() ? PropertyStore.PERFORM_ENVIRONMENT.getValue() : "";
    }

    private void initEnv() {
        if (!this.APP_ENV.isEmpty()) {
            logger.debug(" >>>>>>>>>>>>>>>>>> Environment name : " + this.APP_ENV + "<<<<<<<<<<<<<<<<<<<<<<<<");
            loadAllEnvironmentPropertiesToSystem(this.APP_ENV);
        } else {
            logger.error("Please provide env name");
            assertThat(true, equalTo(false));
        }
    }

    private void loadAllEnvironmentPropertiesToSystem(String environmentName) {
        setSystemPropertiesFromPathUrl(environmentName);
    }

    private void setSystemPropertiesFromPathUrl(String dirName) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        File file = new File(PropertyStore.CONFIG_YAML);
        try {
            Map<String, Object> config = mapper.readValue(file, Map.class);

            Map<String, Object> allEnvironments = (Map<String, Object>) config.get(PropertyStore.ENVIRONMENT);
            Map<String, Object> environment = (Map<String, Object>) allEnvironments.get(dirName);
            for (Map.Entry<String, Object> entry : environment.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                System.setProperty(key, value.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class EnvironmentPropertySingleton {
        private static final EnvironmentProperty INSTANCE = new EnvironmentProperty();
    }

}
