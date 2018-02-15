package com.github.xenteros.supplier;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Set<Supplier> findAllByProductName(String name);
}
