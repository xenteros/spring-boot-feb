package com.github.xenteros.product;

import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    ProductDTO toProductDTO(Product product);

    List<ProductDTO> toProductDTO(Collection<Product> products);
}
