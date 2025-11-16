package com.colonial.endtoend;

import com.colonial.SecurityConfigTest;
import com.colonial.domain.enums.ProductCategory;
import com.colonial.domain.model.Product;
import com.colonial.domain.service.ProductService;
import com.colonial.web.controller.ProductController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(SecurityConfigTest.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductService productService;


    @Autowired
    private ObjectMapper objectMapper;

    // 🔥 GET /products/all
    @Test
    void testGetAll() throws Exception {
        Mockito.when(productService.findAll()).thenReturn(List.of(
                new Product(1, "Cocacola", 3000.0, 10, ProductCategory.BEVERAGE, LocalDateTime.now())
        ));

        mockMvc.perform(get("/products/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Cocacola"));
    }

    // 🔥 POST /products/save
    @Test
    void testSaveProduct() throws Exception {
        Product product = new Product(1, "Aspirina", 150.0, 100, ProductCategory.OTHER, LocalDateTime.now());

        Mockito.when(productService.save(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/products/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Aspirina"));
    }

    // 🔥 GET /products/{id}
    @Test
    void testFindById() throws Exception {
        Product product = new Product(1, "Arroz", 2000.0, 50, ProductCategory.GROCERY, LocalDateTime.now());

        Mockito.when(productService.findById(1)).thenReturn(Optional.of(product));

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Arroz"));
    }

    // 🔥 GET /products/{id} → NotFound
    @Test
    void testFindByIdNotFound() throws Exception {
        Mockito.when(productService.findById(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/products/99"))
                .andExpect(status().isNotFound());
    }

    // 🔥 DELETE /products/{id}
    @Test
    void testDeleteById() throws Exception {

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isNoContent());
    }

    // 🔥 GET /products/search/by-category?category=BEER
    @Test
    void testGetByCategory() throws Exception {
        Mockito.when(productService.findAllByCategory(ProductCategory.BEER))
                .thenReturn(List.of(
                        new Product(1, "Corona", 5000.0, 20, ProductCategory.BEER, LocalDateTime.now())
                ));

        mockMvc.perform(get("/products/search/by-category")
                        .param("category", "BEER"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Corona"));
    }

    // 🔥 GET /products/search/by-name?name=Co
    @Test
    void testGetAllByName() throws Exception {
        Mockito.when(productService.findAllByName("Co"))
                .thenReturn(List.of(new Product(1, "Cocacola", 3000.0, 10, ProductCategory.BEVERAGE, LocalDateTime.now())));

        mockMvc.perform(get("/products/search/by-name")
                        .param("name", "Co"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Cocacola"));
    }

    // 🔥 GET /products/search/name?name=Aspirina
    @Test
    void testGetByExactName() throws Exception {
        Mockito.when(productService.findByName("Aspirina"))
                .thenReturn(Optional.of(new Product(1, "Aspirina", 150.0, 100, ProductCategory.OTHER, LocalDateTime.now())));

        mockMvc.perform(get("/products/search/name")
                        .param("name", "Aspirina"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Aspirina"));
    }

    @Test
    void testGetByExactNameNotFound() throws Exception {
        Mockito.when(productService.findByName("XXX")).thenReturn(Optional.empty());

        mockMvc.perform(get("/products/search/name")
                        .param("name", "XXX"))
                .andExpect(status().isNotFound());
    }

    // 🔥 GET /products/search/exists/{id}
    @Test
    void testExistsById() throws Exception {
        Mockito.when(productService.existsById(1)).thenReturn(true);

        mockMvc.perform(get("/products/exists/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    // 🔥 PUT /products/update/{id}
    @Test
    void testUpdateProduct() throws Exception {
        Product updated = new Product(1, "Chocolate", 5000.0, 30, ProductCategory.SNACKS, LocalDateTime.now());

        Mockito.when(productService.updateProduct(any(Product.class), eq(1))).thenReturn(updated);

        mockMvc.perform(put("/products/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Chocolate"));
    }
}