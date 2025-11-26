package com.linktic.sales.client;

import com.linktic.sales.dto.ProductResponse;
import com.linktic.sales.dto.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class InventoryClient {

    private final RestTemplate rest;

    @Value("${INVENTORY_API_URL}")
    private String inventoryUrl;

    @Value("${API_KEY}")
    private String apiKey;

    public ProductResponse purchase(Long productId, Integer quantity) {

        String url = inventoryUrl + "/inventory/purchase";

        PurchaseRequest body = new PurchaseRequest(productId, quantity);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", apiKey);

        HttpEntity<PurchaseRequest> entity = new HttpEntity<>(body, headers);

        ResponseEntity<ProductResponse> response =
                rest.postForEntity(url, entity, ProductResponse.class);

        return response.getBody();
    }
}
