package com.linktic.inventory;

import com.linktic.inventory.client.ProductClient;
import com.linktic.inventory.dto.*;
import com.linktic.inventory.model.Inventory;
import com.linktic.inventory.repository.InventoryRepository;
import com.linktic.inventory.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InventoryServiceTest {

    private InventoryRepository repo;
    private ProductClient productClient;
    private InventoryService service;

    @BeforeEach
    void setup() {
        repo = mock(InventoryRepository.class);
        productClient = mock(ProductClient.class);
        service = new InventoryService(repo, productClient);
    }

    private ProductResponse mockProduct() {
        ProductAttributes att = new ProductAttributes(1L, "Laptop", "Buena", 2000.0);
        ProductData data = new ProductData(1L, "product", att);
        return new ProductResponse(data);
    }

    @Test
    void getOne_success() {
        Inventory inv = new Inventory(1L, 10);

        when(repo.findById(1L)).thenReturn(Optional.of(inv));

        Inventory result = service.getOne(1L);

        assertEquals(10, result.getQuantity());
    }

    @Test
    void getOne_notFound() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> service.getOne(99L));
    }

    @Test
    void updateStock_success() {
        Inventory inv = new Inventory(1L, 10);

        when(repo.findById(1L)).thenReturn(Optional.of(inv));
        when(repo.save(any())).thenReturn(new Inventory(1L, 20));

        Inventory result = service.updateStock(1L, 20);

        assertEquals(20, result.getQuantity());
    }

    @Test
    void purchase_success() {
        Inventory inv = new Inventory(1L, 10);

        when(repo.findById(1L)).thenReturn(Optional.of(inv));
        when(repo.save(any())).thenReturn(inv);
        when(productClient.getOne(1L)).thenReturn(mockProduct());

        PurchaseRequest req = new PurchaseRequest(1L, 2);

        ProductResponse response = service.purchase(req);

        assertEquals("Laptop", response.data().attributes().nombre());
    }

    @Test
    void purchase_noStock() {
        Inventory inv = new Inventory(1L, 1);

        when(repo.findById(1L)).thenReturn(Optional.of(inv));

        PurchaseRequest req = new PurchaseRequest(1L, 5);

        assertThrows(ResponseStatusException.class, () -> service.purchase(req));
    }
}
