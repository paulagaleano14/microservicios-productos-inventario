package com.linktic.inventory;

import com.linktic.inventory.client.ProductClient;
import com.linktic.inventory.controller.InventoryController;
import com.linktic.inventory.dto.ProductAttributes;
import com.linktic.inventory.dto.ProductData;
import com.linktic.inventory.dto.ProductResponse;
import com.linktic.inventory.dto.PurchaseRequest;
import com.linktic.inventory.model.Inventory;
import com.linktic.inventory.service.InventoryService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InventoryController.class)
class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService service;

    @MockBean
    private ProductClient productClient;

    private static final String API_KEY = "12345";

    @Test
    void getOne_success() throws Exception {
        Inventory inv = new Inventory(1L, 10);

        when(service.getOne(1L)).thenReturn(inv);

        mockMvc.perform(
                        get("/inventory/1")
                                .header("X-API-KEY", API_KEY)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1))
                .andExpect(jsonPath("$.quantity").value(10));
    }

    @Test
    void updateStock_success() throws Exception {
        Inventory inv = new Inventory(1L, 20);

        when(service.updateStock(1L, 20)).thenReturn(inv);

        mockMvc.perform(
                        patch("/inventory/1?quantity=20")
                                .header("X-API-KEY", API_KEY)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.attributes.quantity").value(20));
    }

    @Test
    void purchase_success() throws Exception {
        // Mock del producto
        ProductAttributes attrs = new ProductAttributes(1L, "Laptop", "Gaming", 2500.0);
        ProductData data = new ProductData(1L, "product", attrs);
        ProductResponse response = new ProductResponse(data);

        // Mock del service
        when(service.purchase(any(PurchaseRequest.class)))
                .thenReturn(response);

        mockMvc.perform(
                        post("/inventory/purchase")
                                .contentType("application/json")
                                .header("X-API-KEY", API_KEY)
                                .content("""
                                {
                                   "productId": 1,
                                   "quantity": 5
                                }
                                """)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.attributes.product.data.attributes.nombre").value("Laptop"));
    }
}
