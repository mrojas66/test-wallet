package com.nequi.msfranchise.us_product.controller;

import com.nequi.msfranchise.commons.GeneralBodyResponse;
import com.nequi.msfranchise.entity.FranchiseEntity;
import com.nequi.msfranchise.entity.ProductEntity;
import com.nequi.msfranchise.us_franchise.dto.FranchiseCreateDTO;
import com.nequi.msfranchise.us_franchise.service.FranchiseService;
import com.nequi.msfranchise.us_product.dto.ProductCreateDTO;
import com.nequi.msfranchise.us_product.dto.ProductStockUpdateDTO;
import com.nequi.msfranchise.us_product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    private final MessageSource messageSource;


    @PostMapping
    public ResponseEntity<GeneralBodyResponse> save(@Valid @RequestBody ProductCreateDTO dto, Locale locale) {
        ProductEntity save = service.save(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new GeneralBodyResponse<>(messageSource.getMessage("general.create.success", null, locale), save));
    }

    @PutMapping("/{productId}/stock")
    public ResponseEntity<GeneralBodyResponse> updateStock(@PathVariable UUID productId, @Valid @RequestBody ProductStockUpdateDTO dto, Locale locale) {
        ProductEntity save = service.updateStock(productId, dto.getNewStock());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GeneralBodyResponse<>(messageSource.getMessage("general.update.success", null, locale), save));
    }

    @GetMapping("/top-stock-by-franchise/{franchiseId}")
    public ResponseEntity<GeneralBodyResponse> getTopStockProductsByFranchise(@PathVariable UUID franchiseId, Locale locale) {
        List<ProductEntity> products = service.getTopStockProductsByFranchise(franchiseId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GeneralBodyResponse<>(messageSource.getMessage("general.update.success", null, locale), products));
    }


}
