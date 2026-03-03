package com.example.ux.theme;

import org.springframework.stereotype.Component;

@Component("uiThemeResolver")
public class ThemeResolver {

    public ThemeDescriptor resolve(String imageTag) {
        String safeTag = imageTag != null ? imageTag.toLowerCase() : "";

        if (safeTag.contains("dev")) {
            return new ThemeDescriptor(
                    "Development",
                    "#0f766e",
                    "#99f6e4",
                    "/img/logo-dev.svg",
                    "Development Environment"
            );
        }
        if (safeTag.contains("staging")) {
            return new ThemeDescriptor(
                    "Staging",
                    "#7c3aed",
                    "#ddd6fe",
                    "/img/logo-staging.svg",
                    "Staging Environment"
            );
        }
        if (safeTag.contains("prod") || safeTag.matches("v\\d+\\.\\d+\\.\\d+.*")) {
            return new ThemeDescriptor(
                    "Production",
                    "#1d4ed8",
                    "#bfdbfe",
                    "/img/logo-prod.svg",
                    "Production Environment"
            );
        }

        return new ThemeDescriptor(
                "Default",
                "#4b5563",
                "#e5e7eb",
                "/img/logo-default.svg",
                "Unknown Environment"
        );
    }
}

