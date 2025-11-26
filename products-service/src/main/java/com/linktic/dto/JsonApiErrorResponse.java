package com.linktic.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JsonApiErrorResponse {
    private String status;
    private String title;
    private String detail;
}
