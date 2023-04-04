package Junit;

import configuration2.AllProperties;
import configuration2.DriverHandle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase {
    protected static WebDriver driver;
    private static AllProperties allProperties;
    private static DriverHandle driverHandle;
    static Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeAll
    static void setUp() {
        allProperties = AllProperties.getInstance();
        driverHandle = new DriverHandle();
    }

    @BeforeEach
    void beforeEach() {
        driver = driverHandle.getDriver();
        logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<< Driver initialized >>>>>>>>>>>>>>>>>>>>>>>>>>");
        driver.get(System.getProperty("app_url"));
        logger.info("<<<<<<<<<<<<<<<< Go to url: " + System.getProperty("app_url") + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @AfterEach
    void tearDown() {
        try {
            driver.quit();
            logger.info("<<<<<<<<<<<<<<<<<<<<<< Web driver closed successfully >>>>>>>>>>>>>>>>>>>");
        } catch (Exception e) {
            logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<< The web driver has not been closed! >>>>>>>>>>>>>>>>>>>>>>" + e);
        }
    }
}
