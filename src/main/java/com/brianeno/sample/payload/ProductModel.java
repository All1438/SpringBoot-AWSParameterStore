package com.brianeno.sample.payload;

import com.brianeno.sample.enums.ProductCategory;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Getter
@Component
public class ProductModel {

    private ProductCategory category;
    private String description;
    private BigDecimal price;
}
