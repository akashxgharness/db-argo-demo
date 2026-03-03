package com.example.ux.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "image-tag")
public class ImageTagProperties {

    public enum SourceType {
        FILE,
        ENV
    }

    private SourceType source = SourceType.ENV;
    private String filePath = "/config/config.json";
    private String envVar = "IMAGE_TAG";

    public SourceType getSource() {
        return source;
    }

    public void setSource(SourceType source) {
        this.source = source;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getEnvVar() {
        return envVar;
    }

    public void setEnvVar(String envVar) {
        this.envVar = envVar;
    }
}

