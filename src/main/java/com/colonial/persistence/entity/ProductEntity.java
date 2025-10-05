package com.colonial.persistence.entity;


import com.colonial.domain.enums.ProductCategory;
import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class ProductEntity {

    public ProductEntity(){
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Integer idProduct;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(length = 250)
    private String description;

    @Column(nullable = false)
    private Double acquisitionPrice;

    @Column(nullable = false)
    private Integer stock;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ProductCategory category;

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }


}

