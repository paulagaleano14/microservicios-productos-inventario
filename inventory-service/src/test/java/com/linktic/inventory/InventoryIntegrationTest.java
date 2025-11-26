package com.linktic.inventory;

import com.linktic.inventory.model.Inventory;
import com.linktic.inventory.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class InventoryIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InventoryRepository repo;

    private static final String API_KEY = "12345";

    @BeforeEach
    void setup() {
        repo.deleteAll();
        repo.save(new Inventory(1L, 10)); // <-- NECESARIO para que el test pase
    }

    @Test
    void integration_getOne_success() throws Exception {
        mockMvc.perform(
                        get("/inventory/1")
                                .header("X-API-KEY", API_KEY)
                                .header("Accept", "application/json")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1))
                .andExpect(jsonPath("$.quantity").value(10));
    }

    @Test
    void integration_getOne_notFound() throws Exception {
        mockMvc.perform(
                        get("/inventory/9999")
                                .header("X-API-KEY", API_KEY)
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("No hay inventario para este producto"));
    }
}
