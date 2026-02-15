package com.codewithmosh.store.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data // Generates getters, setters, toString, equals, and hashCode methods
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Byte categoryId;
}
