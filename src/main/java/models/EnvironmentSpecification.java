package models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EnvironmentSpecification {
    boolean active;

    public boolean isActive() {
        return active;
    }

    Map<String, Object> properties = new LinkedHashMap<>();

    @JsonAnySetter
    void setProperties(String key, Object value) {
        properties.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, Object> getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return "EnvironmentSpecification{" +
                "active=" + active +
                ", properties=" + properties +
                '}';
    }
}
