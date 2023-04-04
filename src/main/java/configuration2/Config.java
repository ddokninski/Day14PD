package configuration2;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import models.Browser;
import models.Environment;

public class Config {

    public Environment environment;
    public Browser browser;

    @JsonAnyGetter
    public Environment getEnvironment() {
        return environment;
    }

    @JsonAnyGetter
    public Browser getBrowser() {
        return browser;
    }
}
