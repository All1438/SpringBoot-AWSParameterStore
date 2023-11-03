package com.brianeno.sample.service;

import com.brianeno.sample.dto.ProductDto;
import com.brianeno.sample.payload.ProductModel;
import com.brianeno.sample.entity.Product;
import com.brianeno.sample.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ModelMapper modelMapper;

    public ProductDto create(ProductModel product) {
        var productToSave = Product.builder()
                .description(product.getDescription())
                .category(product.getCategory())
                .price(product.getPrice())
                .build();

        return modelMapper.map(repository.save(productToSave), ProductDto.class);
    }

    public ProductDto findById(long id) {

        var product = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(product, ProductDto.class);
    }

    public List<ProductDto> findAll() {
        var productList = repository.findAll().stream().sorted(Comparator.comparing(Product::getId)).toList();
        return productList
            .stream()
            .map(p -> modelMapper.map(p, ProductDto.class))
            .collect(Collectors.toList());
    }

    public void updateOne(long id, ProductModel product) {
        if (repository.findById(id).isEmpty()) {
            throw new EntityNotFoundException();
        }
        repository.updateById(product.getDescription(), product.getCategory().toString(), product.getPrice(), id);
    }

    public ProductDto patchOne(long id, JsonPatch patch) {
        var product = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        var productPatched = applyPatchToProduct(patch, product);
        return modelMapper.map(repository.save(productPatched), ProductDto.class);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    private Product applyPatchToProduct(JsonPatch patch, Product product) {
        try {
            var objectMapper = new ObjectMapper();
            JsonNode patched = patch.apply(objectMapper.convertValue(product, JsonNode.class));
            return objectMapper.treeToValue(patched, Product.class);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
