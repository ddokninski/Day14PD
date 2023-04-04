package configuration2;

import models.Browser;
import models.EnvironmentSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class AllProperties {
    static Logger logger = LoggerFactory.getLogger(AllProperties.class);
    YamlReader yamlReader = new YamlReader();
    private Browser browser;
    private List<EnvironmentSpecification> listOfEnvironments;

    private AllProperties() {
        setBrowserProperties();
        setEnvironmentProperties();
    }

    public static AllProperties getInstance() {
        return AllProperties.AllPropertiesSingleton.INSTANCE;
    }

    private void setEnvironmentProperties() {
        listOfEnvironments = yamlReader.getConfig().getEnvironment().getEnvironments();
        for (EnvironmentSpecification environmentSpecification : listOfEnvironments) {
            if (environmentSpecification.isActive()) {
                Map<String, Object> envProperties = environmentSpecification.getProperties();
                for (Map.Entry entry : envProperties.entrySet()) {
                    System.setProperty(entry.getKey().toString(), entry.getValue().toString());
                    logger.info("Load env properties: {} = {}", entry.getKey().toString(), entry.getValue().toString());
                }
                break;
            }
        }
    }

    private void setBrowserProperties() {
        browser = yamlReader.getConfig().getBrowser();
        Map<String, Object> browserProperties = browser.getBrowserProperties();
        for (Map.Entry entry : browserProperties.entrySet()) {
            System.setProperty(entry.getKey().toString(), entry.getValue().toString());
            logger.info("Load browser properties: {} = {}", entry.getKey().toString(), entry.getValue().toString());
        }
    }


    public static class AllPropertiesSingleton {
        private static final AllProperties INSTANCE = new AllProperties();
    }
}
