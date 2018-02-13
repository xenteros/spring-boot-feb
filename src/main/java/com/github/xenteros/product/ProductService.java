package com.github.xenteros.product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<ProductDTO> findAll();
    ProductDTO addProduct(ProductDTO product);
    ProductDTO updatePrice(Long productId, BigDecimal newPrice);
    void deleteProduct(Long productId);
}
