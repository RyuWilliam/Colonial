package com.colonial.domain.model;

import com.colonial.domain.enums.ProductCategory;
import java.time.LocalDateTime;
public class Product {
    private Integer idProduct;
    private String name;
    private String description;
    private Double acquisitionPrice;
    private Integer stock;
    private ProductCategory category;
    private LocalDateTime lastUpdated;

    public Product(Integer idProduct, String name, Double acquisitionPrice, Integer stock, ProductCategory category, LocalDateTime lastUpdated) {
        this.idProduct = idProduct;
        this.name = name;
        this.acquisitionPrice = acquisitionPrice;
        this.stock = stock;
        this.category = category;
        this.lastUpdated = lastUpdated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product(){

    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAcquisitionPrice() {
        return acquisitionPrice;
    }

    public void setAcquisitionPrice(Double acquisitionPrice) {
        this.acquisitionPrice = acquisitionPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}