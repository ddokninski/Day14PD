package configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;

public class BrowserEnvironment {
    private String browserName;
    private boolean headlessBrowser;
    private int webElementTimeout;
    private boolean attachScreenshot;
    private Logger logger;
    private WebDriver driver;

    public BrowserEnvironment() {
        this.headlessBrowser = false;
        this.webElementTimeout = 10;
        this.attachScreenshot = false;
        this.browserName = "chrome";
        this.logger = LoggerFactory.getLogger(BrowserEnvironment.class);
        setBrowserPropertiesFromPathUrl();
        this.initBrowserSettings();
    }

    private void initBrowserSettings() {
        this.browserName = System.getProperty(PropertyStore.PERFORM_BROWSER).isEmpty() ? this.browserName : System.getProperty(PropertyStore.PERFORM_BROWSER);
        this.webElementTimeout = System.getProperty(PropertyStore.BROWSER_WEBELEMENT_TIMEOUT).isEmpty() ? this.webElementTimeout : Integer.parseInt(System.getProperty(PropertyStore.BROWSER_WEBELEMENT_TIMEOUT));
        this.attachScreenshot = System.getProperty(PropertyStore.BROWSER_ATTACH_SCREENSHOT).isEmpty() ? this.attachScreenshot : Boolean.parseBoolean(System.getProperty(PropertyStore.BROWSER_ATTACH_SCREENSHOT));
        this.headlessBrowser = System.getProperty(PropertyStore.BROWSER_HEADLESS).isEmpty() ? this.headlessBrowser : Boolean.parseBoolean(System.getProperty(PropertyStore.BROWSER_HEADLESS));
    }

    private void setBrowserPropertiesFromPathUrl() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        File file = new File("src/main/resources/config.yaml");
        try {
            Map<String, Object> config = mapper.readValue(file, Map.class);

            Map<String, Object> browserProperties = (Map<String, Object>) config.get(PropertyStore.BROWSER);
            System.out.println(browserProperties);
            for (Map.Entry<String, Object> entry : browserProperties.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                System.setProperty(key, value.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebDriver getDriver() {
        WebDriver driver;
        switch (this.browserName) {
            case "chrome":
                ChromeOptions optionsChrome = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                optionsChrome.addArguments("start-maximized", "--remote-allow-origins=*");
                driver = new ChromeDriver(optionsChrome);
                driver.get(System.getProperty("webUrl"));
                break;
            case "firefox":
                FirefoxOptions optionsFirefox = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                optionsFirefox.addArguments("start-maximized");
                driver = new FirefoxDriver(optionsFirefox);
                driver.get(System.getProperty("webUrl"));
                break;
            default:
                InternetExplorerOptions optionsDefault = new InternetExplorerOptions();
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver(optionsDefault);
                driver.get(System.getProperty("webUrl"));
        }
        this.driver = driver;
        return this.driver;
    }
}
