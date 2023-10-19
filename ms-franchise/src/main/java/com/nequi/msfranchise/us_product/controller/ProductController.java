package com.nequi.msfranchise.us_product.controller;

import com.nequi.msfranchise.commons.GeneralBodyResponse;
import com.nequi.msfranchise.entity.ProductEntity;
import com.nequi.msfranchise.us_product.dto.ProductCreateDTO;
import com.nequi.msfranchise.us_product.dto.ProductStockUpdateDTO;
import com.nequi.msfranchise.us_product.dto.ProductUpdateDTO;
import com.nequi.msfranchise.us_product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Product Management", description = "Endpoints for managing products")
public class ProductController {
    private final ProductService service;
    private final MessageSource messageSource;


    @PostMapping
    @Operation(summary = "Add a new product", description = "Add a new product and return the created product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created product"),
            @ApiResponse(responseCode = "400", description = "Invalid product details provided")
    })
    public ResponseEntity<GeneralBodyResponse<ProductEntity>> save(@Valid @RequestBody ProductCreateDTO dto, Locale locale) {
        ProductEntity save = service.save(dto, locale);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new GeneralBodyResponse<>(messageSource.getMessage("general.create.success", null, locale), save));
    }

    @PutMapping("/{productId}/stock")
    @Operation(summary = "Update product's stock", description = "Update the stock of a product given its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated stock"),
            @ApiResponse(responseCode = "400", description = "Invalid stock details provided"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<GeneralBodyResponse<ProductEntity>> updateStock(@PathVariable UUID productId, @Valid @RequestBody ProductStockUpdateDTO dto, Locale locale) {
        ProductEntity save = service.updateStock(productId, dto.getNewStock(), locale);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GeneralBodyResponse<>(messageSource.getMessage("general.update.success", null, locale), save));
    }

    @GetMapping("/top-stock-by-franchise/{franchiseId}")
    @Operation(summary = "Retrieve products with top stock by franchise", description = "Return a list of products that have the highest stock for a particular franchise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved products"),
            @ApiResponse(responseCode = "404", description = "Franchise or product not found")
    })
    public ResponseEntity<GeneralBodyResponse<List<ProductEntity>>> getTopStockProductsByFranchise(@PathVariable UUID franchiseId, Locale locale) {
        List<ProductEntity> products = service.getTopStockProductsByFranchise(franchiseId, locale);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GeneralBodyResponse<>(messageSource.getMessage("general.get.success", null, locale), products));
    }

    @PutMapping("/{productId}/name")
    @Operation(summary = "Update product's name", description = "Update the name of a product given its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated product name"),
            @ApiResponse(responseCode = "400", description = "Invalid name provided"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<GeneralBodyResponse<ProductEntity>> updateName(@PathVariable UUID productId, @RequestBody ProductUpdateDTO dto, Locale locale) {
        ProductEntity save = service.updateName(productId, dto.getName(), locale);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GeneralBodyResponse<>(messageSource.getMessage("general.create.success", null, locale), save));
    }

    @GetMapping
    @Operation(summary = "Retrieve all products", description = "Return a paginated list of all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved products"),
    })
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
