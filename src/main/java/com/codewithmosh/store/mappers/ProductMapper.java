package com.codewithmosh.store.mappers;

import com.codewithmosh.store.dtos.ProductDto;
import com.codewithmosh.store.dtos.RegisterUserRequest;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring") // Specify that this is a Spring component
public interface ProductMapper {

    @Mapping(target = "categoryId", source = "category.id") // Map category.id to categoryId in ProductDto
    ProductDto toDto(Product product); // Entity to DTO

    Product toEntity(ProductDto productDto); // DTO to Entity

    @Mapping(target = "id", ignore = true) // Ignore the id field during update
    void updateProduct(ProductDto productDto, @MappingTarget Product product); // Method to update existing Product entity. With @MappingTarget annotation to indicate the target object to be updated. otherwise, MapStruct give a runtime error.
}