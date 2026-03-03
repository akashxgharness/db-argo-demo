package com.example.ux.config.source;

public interface ConfigSource {

    /**
     * Returns the current image tag, or an empty string if not available.
     */
    String getImageTag();
}

