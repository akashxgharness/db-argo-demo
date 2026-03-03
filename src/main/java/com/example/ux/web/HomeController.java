package com.example.ux.web;

import com.example.ux.config.source.ConfigSource;
import com.example.ux.theme.ThemeDescriptor;
import com.example.ux.theme.ThemeResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ConfigSource configSource;
    private final ThemeResolver themeResolver;

    public HomeController(ConfigSource configSource, ThemeResolver themeResolver) {
        this.configSource = configSource;
        this.themeResolver = themeResolver;
    }

    @GetMapping("/")
    public String index(Model model) {
        String imageTag = configSource.getImageTag();
        ThemeDescriptor theme = themeResolver.resolve(imageTag);

        model.addAttribute("imageTag", imageTag);
        model.addAttribute("theme", theme);

        return "index";
    }
}

