package com.example.ux.theme;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThemeResolverTests {

    private final ThemeResolver resolver = new ThemeResolver();

    @Test
    void resolvesDevTheme() {
        ThemeDescriptor theme = resolver.resolve("dev-123");
        assertEquals("Development", theme.getName());
    }

    @Test
    void resolvesStagingTheme() {
        ThemeDescriptor theme = resolver.resolve("staging-abc");
        assertEquals("Staging", theme.getName());
    }

    @Test
    void resolvesProdThemeForProdKeyword() {
        ThemeDescriptor theme = resolver.resolve("prod-1.0.0");
        assertEquals("Production", theme.getName());
    }

    @Test
    void resolvesProdThemeForSemanticVersion() {
        ThemeDescriptor theme = resolver.resolve("v1.2.3");
        assertEquals("Production", theme.getName());
    }

    @Test
    void resolvesDefaultThemeForUnknown() {
        ThemeDescriptor theme = resolver.resolve("feature-x");
        assertEquals("Default", theme.getName());
    }
}

