package com.github.xenteros.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDTO> findAll() {
        return productMapper.toProductDTO(productRepository.findAll());
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setPrice(productDTO.getPrice());
        product.setName(productDTO.getName());
        return productMapper.toProductDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO updatePrice(Long productId, BigDecimal newPrice) {

        Product product = productRepository.findOne(productId);
        product.setPrice(newPrice);
        return productMapper.toProductDTO(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.delete(productId);
    }
}
