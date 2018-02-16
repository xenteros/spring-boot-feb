package com.github.xenteros.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o where o.uuid = ?1")
    Set<Order> findAllByUuid(String uuid);
}
