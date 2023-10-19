package com.nequi.msfranchise.us_product.controller;

import com.nequi.msfranchise.commons.GeneralBodyResponse;
import com.nequi.msfranchise.entity.ProductEntity;
import com.nequi.msfranchise.us_product.dto.ProductCreateDTO;
import com.nequi.msfranchise.us_product.dto.ProductStockUpdateDTO;
import com.nequi.msfranchise.us_product.dto.ProductUpdateDTO;
import com.nequi.msfranchise.us_product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<GeneralBodyResponse<ProductEntity>> save(@Valid @RequestBody ProductCreateDTO dto, Locale locale) {
        ProductEntity save = service.save(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new GeneralBodyResponse<>(messageSource.getMessage("general.create.success", null, locale), save));
    }

    @PutMapping("/{productId}/stock")
    public ResponseEntity<GeneralBodyResponse<ProductEntity>> updateStock(@PathVariable UUID productId, @Valid @RequestBody ProductStockUpdateDTO dto, Locale locale) {
        ProductEntity save = service.updateStock(productId, dto.getNewStock());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GeneralBodyResponse<>(messageSource.getMessage("general.update.success", null, locale), save));
    }

    @GetMapping("/top-stock-by-franchise/{productId}")
    public ResponseEntity<GeneralBodyResponse<List<ProductEntity>>> getTopStockProductsByFranchise(@PathVariable UUID productId, Locale locale) {
        List<ProductEntity> products = service.getTopStockProductsByFranchise(productId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GeneralBodyResponse<>(messageSource.getMessage("general.update.success", null, locale), products));
    }

    @PutMapping("/{productId}/name")
    public ResponseEntity<GeneralBodyResponse<ProductEntity>> updateName(@PathVariable UUID productId, @RequestBody ProductUpdateDTO dto, Locale locale) {
        ProductEntity save = service.updateName(productId, dto.getName());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GeneralBodyResponse<>(messageSource.getMessage("general.create.success", null, locale), save));
    }

    @GetMapping
    public ResponseEntity<GeneralBodyResponse<Page<ProductEntity>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Locale locale) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ProductEntity> all = service.getAll(pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GeneralBodyResponse<>(messageSource.getMessage("general.get.success", null, locale), all));
    }


}
