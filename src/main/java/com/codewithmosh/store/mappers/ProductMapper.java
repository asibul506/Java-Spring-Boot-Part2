package com.codewithmosh.store.mappers;

import com.codewithmosh.store.dtos.ProductDto;
import com.codewithmosh.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") // Specify that this is a Spring component
public interface ProductMapper {

    @Mapping(target = "categoryId", source = "category.id") // Map category.id to categoryId in ProductDto
    ProductDto toDto(Product product); // Entity to DTO
}