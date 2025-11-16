package com.colonial.endtoend;

import com.colonial.SecurityConfigTest;
import com.colonial.domain.enums.TransactionType;
import com.colonial.domain.model.Transaction;
import com.colonial.domain.service.TransactionService;
import com.colonial.web.controller.TransactionController;
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

@WebMvcTest(TransactionController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(SecurityConfigTest.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ObjectMapper objectMapper;

    // 🔥 GET /transactions/all
    @Test
    void testGetAll() throws Exception {
        Mockito.when(transactionService.findAll())
                .thenReturn(List.of(
                        new Transaction(1, 2, TransactionType.SALE, LocalDateTime.now(), List.of())
                ));

        mockMvc.perform(get("/transactions/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].type").value("SALE"));
    }

    // 🔥 POST /transactions/save
    @Test
    void testSaveTransaction() throws Exception {
        Transaction transaction = new Transaction(
                1,
                2,
                TransactionType.SALE,
                LocalDateTime.now(),
                List.of()
        );

        Mockito.when(transactionService.save(any(Transaction.class))).thenReturn(transaction);

        mockMvc.perform(post("/transactions/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.type").value("SALE"));
    }

    // 🔥 GET /transactions/{id}
    @Test
    void testFindById() throws Exception {
        Transaction tx = new Transaction(
                1, 2, TransactionType.PURCHASE, LocalDateTime.now(), List.of()
        );

        Mockito.when(transactionService.findById(1)).thenReturn(Optional.of(tx));

        mockMvc.perform(get("/transactions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("PURCHASE"));
    }

    // 🔥 GET /transactions/{id} → NotFound
    @Test
    void testFindByIdNotFound() throws Exception {
        Mockito.when(transactionService.findById(999)).thenReturn(Optional.empty());

        mockMvc.perform(get("/transactions/999"))
                .andExpect(status().isNotFound());
    }

    // 🔥 DELETE /transactions/{id}
    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/transactions/10"))
                .andExpect(status().isNoContent());
    }

    // 🔥 GET /transactions/search/by-date
    @Test
    void testGetByDateBetween() throws Exception {
        LocalDateTime start = LocalDateTime.of(2025, 1, 1, 0, 0);
        LocalDateTime end = LocalDateTime.of(2025, 1, 31, 23, 59);

        Mockito.when(transactionService.findByDateBetween(start, end))
                .thenReturn(List.of(
                        new Transaction(1, 2, TransactionType.SALE, start, List.of())
                ));

        mockMvc.perform(get("/transactions/search/by-date")
                        .param("start", start.toString())
                        .param("end", end.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].type").value("SALE"));
    }

    // 🔥 GET /transactions/search/by-type
    @Test
    void testGetByType() throws Exception {
        Mockito.when(transactionService.findByType(TransactionType.SALE))
                .thenReturn(List.of(
                        new Transaction(1, 3, TransactionType.SALE, LocalDateTime.now(), List.of())
                ));

        mockMvc.perform(get("/transactions/search/by-type")
                        .param("type", "SALE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].type").value("SALE"));
    }
}
