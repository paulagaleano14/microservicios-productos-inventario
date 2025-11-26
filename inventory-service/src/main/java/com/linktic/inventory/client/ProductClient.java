package com.linktic.inventory.client;

import com.linktic.inventory.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ProductClient {

    private final RestTemplate rest;

    @Value("${PRODUCTS_API_URL}")
    private String baseUrl;

    @Value("${app.api.key}")
    private String apiKey;

    @Retryable(
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000),
            value = Exception.class
    )
    public ProductResponse getOne(Long id) {

        // 1. Crear headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", apiKey);

        // 2. Crear request
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        // 3. Llamada con headers + manejo de errores
        ResponseEntity<ProductResponse> response = rest.exchange(
                baseUrl + "/products/" + id,
                HttpMethod.GET,
                entity,
                ProductResponse.class
        );

        return response.getBody();
    }
}
