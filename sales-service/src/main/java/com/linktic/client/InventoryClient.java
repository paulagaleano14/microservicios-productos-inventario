package com.linktic.sales.client;

import com.linktic.sales.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.linktic.sales.dto.PurchaseRequest;

@Component
public class InventoryClient {

    private final RestTemplate rest;
    private final String inventoryUrl;

    public InventoryClient(RestTemplate rest,
                           @Value("${INVENTORY_API_URL}") String inventoryUrl) {
        this.rest = rest;
        this.inventoryUrl = inventoryUrl;
    }

    public ProductResponse purchase(Long productId, Integer quantity) {

        String url = inventoryUrl + "/purchase";

        var body = new PurchaseRequest(productId, quantity);

        var response = rest.postForEntity(url, body, ProductResponse.class);

        return response.getBody();
    }
}
