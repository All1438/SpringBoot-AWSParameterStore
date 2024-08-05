package com.brianeno.sample.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Value(value = "${application.id}")
    private String applicationId;

    @Value(value = "${environment}")
    private String environment;

    @Value(value = "${categories.types:#{null}}")
    private String[] categoryTypes;

    @Value("${spring.profiles.active:none}")
    private String currentActiveEnv;

    @Value(value = "${application.title:#{null}}")
    private String applicationTitle;


    @RequestMapping(value = "/props", method = RequestMethod.GET)
    public Map<String, Object> getProperties(HttpServletRequest request) {

        final Map<String, Object> map = new HashMap<>();
        map.put("currentProfile", currentActiveEnv);
        map.put("applicationId", applicationId);
        map.put("environment", environment);
        map.put("types", categoryTypes);
        map.put("title", applicationTitle);
        return map;
    }

    
}
