package com.github.xenteros.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> findAllProducts() {
        return productService.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO product) {
        ProductDTO newProduct = productService.addProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ProductDTO updatePrice(@PathVariable Long id, @RequestParam BigDecimal price) {
        return productService.updatePrice(id, price);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/interface/all")
    public Set<String> findAllInteface() {
        return productService.findAllInterface().stream()
                .flatMap(p -> Stream.of(p.getName(), p.getPrice().toString()))
                .collect(Collectors.toSet());
    }

}
