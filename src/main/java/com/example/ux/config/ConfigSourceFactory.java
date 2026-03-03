package com.example.ux.config;

import com.example.ux.config.source.ConfigSource;
import com.example.ux.config.source.EnvConfigSource;
import com.example.ux.config.source.FileConfigSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigSourceFactory {

    @Bean
    public ConfigSource configSource(ImageTagProperties properties,
                                     EnvConfigSource envConfigSource,
                                     FileConfigSource fileConfigSource) {
        if (properties.getSource() == ImageTagProperties.SourceType.FILE) {
            return fileConfigSource;
        }
        return envConfigSource;
    }
}

