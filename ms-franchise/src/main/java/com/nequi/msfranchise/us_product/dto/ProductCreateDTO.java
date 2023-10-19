package com.nequi.msfranchise.us_product.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class ProductCreateDTO {
    private UUID branchId;
    private String name;
    private Double unitPrice;
    private Integer stock;
}
