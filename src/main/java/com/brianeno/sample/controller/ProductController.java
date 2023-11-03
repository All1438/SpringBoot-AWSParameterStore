package com.brianeno.sample.controller;

import com.brianeno.sample.dto.ProductDto;
import com.brianeno.sample.payload.ProductModel;
import com.brianeno.sample.service.ProductService;
import com.brianeno.sample.entity.Product;
import com.github.fge.jsonpatch.JsonPatch;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

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

    @PostMapping()
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductModel product) {
        return new ResponseEntity<>(service.create(product), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getOneProduct(@PathVariable("id") int id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateOneProduct(@PathVariable("id") int id, @RequestBody ProductModel product) {
        service.updateOne(id, product);
    }

    @PatchMapping(value = "/update/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<ProductDto> patchOneProduct(@PathVariable("id") int id, @RequestBody JsonPatch patch) {
        return new ResponseEntity<>(service.patchOne(id, patch),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOneProduct(@PathVariable("id") int id) {
        service.deleteById(id);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllProducts() {
        service.deleteAll();
    }
}
