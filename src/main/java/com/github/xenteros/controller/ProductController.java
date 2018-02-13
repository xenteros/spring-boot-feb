package com.github.xenteros.controller;

import com.github.xenteros.model.Product;
import com.github.xenteros.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PatchMapping("/{id}")
    public Product updatePrice(@PathVariable Long id, @RequestParam BigDecimal price) {
        Product product = productRepository.findOne(id);
        product.setPrice(price);
        return productRepository.save(product);
    }

}
