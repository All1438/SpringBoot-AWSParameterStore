package com.brianeno.sample.dto;

import com.brianeno.sample.enums.ProductCategory;
import java.math.BigDecimal;

public class ProductDto {
  private long id;

  private ProductCategory category;

  private String description;

  private BigDecimal price;
}
