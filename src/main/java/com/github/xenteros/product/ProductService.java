package com.github.xenteros.product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ProductService {

    List<ProductDTO> findAll();
    ProductDTO addProduct(ProductDTO product);
    ProductDTO updatePrice(Long productId, BigDecimal newPrice);

    Set<ProductNameAndPrice> findAllInterface();

    void deleteProduct(Long productId);
}
