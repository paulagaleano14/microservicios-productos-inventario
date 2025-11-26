package com.linktic.products.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linktic.products.controller.ProductController;
import com.linktic.products.dto.ProductRequest;
import com.linktic.products.dto.ProductResponse;
import com.linktic.products.service.ProductService;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ProductService service;

    @Test
    void testCreateProduct() throws Exception {

        ProductRequest req = new ProductRequest();
        req.setNombre("Laptop");
        req.setPrecio(1200.0);

        ProductResponse mockResponse = new ProductResponse();
        mockResponse.setId(1L);
        mockResponse.setNombre("Laptop");
        mockResponse.setPrecio(1200.0);

        Mockito.when(service.create(Mockito.any()))
                .thenReturn(mockResponse);

        mvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1));
    }
}
