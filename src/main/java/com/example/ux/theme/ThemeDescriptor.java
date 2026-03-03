package com.example.ux.theme;

public class ThemeDescriptor {

    private final String name;
    private final String primaryColor;
    private final String secondaryColor;
    private final String logoPath;
    private final String bannerText;

    public ThemeDescriptor(String name, String primaryColor, String secondaryColor, String logoPath, String bannerText) {
        this.name = name;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.logoPath = logoPath;
        this.bannerText = bannerText;
    }

    public String getName() {
        return name;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public String getBannerText() {
        return bannerText;
    }
}

