package com.example.ux.config.source;

import com.example.ux.config.ImageTagProperties;
import org.springframework.stereotype.Component;

@Component
public class EnvConfigSource implements ConfigSource {

    private final ImageTagProperties properties;

    public EnvConfigSource(ImageTagProperties properties) {
        this.properties = properties;
    }

    @Override
    public String getImageTag() {
        String envVarName = properties.getEnvVar();
        String value = System.getenv(envVarName);
        return value != null ? value : "";
    }
}

