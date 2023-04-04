package models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Environment {

    Map<String, EnvironmentSpecification> environments = new LinkedHashMap<>();

    @JsonAnySetter
    void setEnvironment(String key, EnvironmentSpecification envSpecification) {environments.put(key, envSpecification);}

    @JsonAnyGetter
    public List<EnvironmentSpecification> getEnvironments() {return environments.values().stream().toList();}

    @Override
    public String toString() {
        return "Environment{" +
                "environments=" + environments +
                '}';
    }
}
