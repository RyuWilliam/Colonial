package com.colonial.unitaries;

import com.colonial.domain.enums.TransactionType;
import com.colonial.domain.model.Transaction;
import com.colonial.domain.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionRepositoryTest {

    private TransactionRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(TransactionRepository.class);
    }

    @Test
    void testSave() {
        Transaction tx = new Transaction();
        tx.setIdTransaction(1);

        when(repository.save(tx)).thenReturn(tx);

        Transaction saved = repository.save(tx);

        assertNotNull(saved);
        assertEquals(1, saved.getIdTransaction());
    }

    @Test
    void testFindById() {
        Transaction tx = new Transaction();
        tx.setIdTransaction(10);

        when(repository.findById(10)).thenReturn(Optional.of(tx));

        Optional<Transaction> result = repository.findById(10);

        assertTrue(result.isPresent());
        assertEquals(10, result.get().getIdTransaction());
    }

    @Test
    void testFindByUserId() {
        Transaction tx1 = new Transaction();
        tx1.setIdUser(5);

        when(repository.findByUserId(5)).thenReturn(List.of(tx1));

        List<Transaction> list = repository.findByUserId(5);

        assertEquals(1, list.size());
        assertEquals(5, list.get(0).getIdUser());
    }

    @Test
    void testFindByDateBetween() {
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();

        when(repository.findByDateBetween(start, end)).thenReturn(Arrays.asList(new Transaction()));

        List<Transaction> list = repository.findByDateBetween(start, end);

        assertFalse(list.isEmpty());
    }

    @Test
    void testFindByType() {
        TransactionType type = TransactionType.SALE;

        when(repository.findByType(type)).thenReturn(List.of(new Transaction()));

        List<Transaction> list = repository.findByType(type);

        assertEquals(1, list.size());
    }

    @Test
    void testDeleteById() {
        doNothing().when(repository).deleteById(3);

        repository.deleteById(3);

        verify(repository, times(1)).deleteById(3);
    }
}