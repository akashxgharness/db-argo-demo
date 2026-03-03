package com.example.ux.config.source;

import com.example.ux.config.ImageTagProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileConfigSource implements ConfigSource {

    private final ImageTagProperties properties;
    private final ObjectMapper objectMapper;

    public FileConfigSource(ImageTagProperties properties, ObjectMapper objectMapper) {
        this.properties = properties;
        this.objectMapper = objectMapper;
    }

    @Override
    public String getImageTag() {
        Path path = Path.of(properties.getFilePath());
        if (!Files.exists(path)) {
            return "";
        }
        try {
            byte[] bytes = Files.readAllBytes(path);
            JsonNode root = objectMapper.readTree(bytes);
            JsonNode tagNode = root.path("imageTag");
            if (tagNode.isTextual()) {
                return tagNode.asText();
            }
        } catch (IOException ignored) {
            // fall through to default
        }
        return "";
    }
}

