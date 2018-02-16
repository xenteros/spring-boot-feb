package com.github.xenteros.product;

import com.github.xenteros.common.BaseEntity;
import com.github.xenteros.order.Order;
import com.github.xenteros.supplier.Supplier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Product extends BaseEntity implements ProductNameAndPrice {

    @Column(unique = true, nullable = false, length = 60)
    private String name;

    @DecimalMin("0")
    @Column(nullable = false)
    private BigDecimal price;

//    @BatchSize(size = 25)
    @OneToMany(mappedBy = "product")
    private List<Supplier> suppliers;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

    public Product() {
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
