package com.linktic.inventory.client;

import com.linktic.inventory.dto.ProductResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@Component
public class ProductClient {

    private final RestTemplate rest;
    private final String baseUrl;

    public ProductClient(RestTemplate rest, @Value("${PRODUCTS_API_URL}") String baseUrl) {
        this.rest = rest;
        this.baseUrl = baseUrl;
    }

    public ProductResponse getOne(Long id) {
        return rest.getForObject(baseUrl + "/products/" + id, ProductResponse.class);
    }
}
