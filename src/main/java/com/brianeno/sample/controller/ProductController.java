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
    @Value(value = "${url}")
    private String url;

    @Value(value = "${user}")
    private String user;

    @Value(value = "${password}")
    private String password;


    @RequestMapping(value = "/props", method = RequestMethod.GET)
    public Map<String, Object> getProperties(HttpServletRequest request) {

        final Map<String, Object> map = new HashMap<>();
        map.put("url", url);
        map.put("user", user);
        map.put("password", password);
        return map;
    }

    
}
