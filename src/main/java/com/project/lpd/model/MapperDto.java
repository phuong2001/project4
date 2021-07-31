package com.project.lpd.model;

import com.project.lpd.entity.ProductEntity;
import com.project.lpd.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface MapperDto {
    UserEntity convertToEntity(UserDto userDto);
    UserDto convertToDto(UserEntity userEntity);
    ProductEntity convertEntityProduct(ProductDto productDto);
}
