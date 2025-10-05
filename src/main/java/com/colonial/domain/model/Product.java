package com.colonial.domain.model;

import com.colonial.domain.enums.ProductCategory;
import jakarta.validation.constraints.*;

import java.util.Objects;

public class Product {
    private int idProduct;
    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @Size(max = 255, message = "La descripción no puede superar 255 caracteres")
    private String description;

    @NotNull(message = "El precio de adquisición es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private Double acquisitionPrice;

    @Min(value = 0, message = "El stock no puede ser negativo")
    private int stock;

    @NotNull(message = "La categoría es obligatoria")
    private ProductCategory category;


    public boolean isAvailable() {
        return stock > 0;
    }

    public Double getSellPrice() {
        return Math.ceil(acquisitionPrice * 1.2);
    }
    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAcquisitionPrice() {
        return acquisitionPrice;
    }

    public void setAcquisitionPrice(Double acquisitionPrice) {
        this.acquisitionPrice = acquisitionPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return idProduct == product.idProduct;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct);
    }
}
