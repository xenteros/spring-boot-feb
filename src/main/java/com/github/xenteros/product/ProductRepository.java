package com.github.xenteros.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query("SELECT p FROM Product p where p.name = ?1")
    Set<Product> findAllByName(String name);

    Set<ProductNameAndPrice> findAllByPriceGreaterThan(BigDecimal bd);

}
