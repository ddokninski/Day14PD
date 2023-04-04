package Junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TitleTest2 extends TestBase{

    @Test
    @DisplayName("YAML")
    void checkTitleForWeb() {
        String actualTitle = driver.getTitle();
        assertThat(actualTitle).isEqualTo(System.getProperty("title"));
    }
}
