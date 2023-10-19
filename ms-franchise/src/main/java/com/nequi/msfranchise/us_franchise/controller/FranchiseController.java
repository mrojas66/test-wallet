package com.nequi.msfranchise.us_franchise.controller;

import com.nequi.msfranchise.commons.GeneralBodyResponse;
import com.nequi.msfranchise.entity.FranchiseEntity;
import com.nequi.msfranchise.us_franchise.dto.FranchiseCreateDTO;
import com.nequi.msfranchise.us_franchise.dto.FranchiseUpdateDTO;
import com.nequi.msfranchise.us_franchise.service.FranchiseService;
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

import java.util.Locale;
import java.util.UUID;

@RestController
@RequestMapping("franchise")
@RequiredArgsConstructor
@Tag(name = "Franchise Management", description = "Endpoints for managing franchises")
public class FranchiseController {
    private final FranchiseService service;
    private final MessageSource messageSource;


    @PostMapping
    @Operation(summary = "Create a new franchise", description = "Create and return a new franchise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created franchise"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
    })
    public ResponseEntity<GeneralBodyResponse<FranchiseEntity>> save(@Valid @RequestBody FranchiseCreateDTO dto, Locale locale) {
        FranchiseEntity save = service.save(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new GeneralBodyResponse<>(messageSource.getMessage("general.create.success", null, locale), save));
    }

    @PutMapping("/{franchiseId}/name")
    @Operation(summary = "Update franchise's name", description = "Update the name of a franchise given its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated franchise name"),
            @ApiResponse(responseCode = "400", description = "Invalid name provided"),
            @ApiResponse(responseCode = "404", description = "Franchise not found")
    })
    public ResponseEntity<GeneralBodyResponse<FranchiseEntity>> updateName(@PathVariable UUID franchiseId, @RequestBody FranchiseUpdateDTO dto, Locale locale) {
        FranchiseEntity save = service.updateName(franchiseId, dto.getName(), locale);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GeneralBodyResponse<>(messageSource.getMessage("general.update.success", null, locale), save));
    }

    @GetMapping
    @Operation(summary = "Retrieve all franchises", description = "Return a paginated list of all franchises")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved franchises"),
    })
    public ResponseEntity<GeneralBodyResponse<Page<FranchiseEntity>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Locale locale) {

        Pageable pageable = PageRequest.of(page, size);
        Page<FranchiseEntity> all = service.getAll(pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GeneralBodyResponse<>(messageSource.getMessage("general.get.success", null, locale), all));
    }
}
