package com.linktic.products.service;

import com.linktic.products.dto.ProductRequest;
import com.linktic.products.exception.ProductNotFoundException;
import com.linktic.products.model.Product;
import com.linktic.products.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    private final ProductRepository repo = Mockito.mock(ProductRepository.class);
    private final ProductService service = new ProductService(repo);

    @Test
    void testGetById_NotFound() {
        Mockito.when(repo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> service.getById(1L));
    }
}
