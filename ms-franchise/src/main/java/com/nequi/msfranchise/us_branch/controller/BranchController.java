package com.nequi.msfranchise.us_branch.controller;

import com.nequi.msfranchise.commons.GeneralBodyResponse;
import com.nequi.msfranchise.entity.BranchEntity;
import com.nequi.msfranchise.us_branch.dto.BranchCreateDTO;
import com.nequi.msfranchise.us_branch.dto.BranchUpdateDTO;
import com.nequi.msfranchise.us_branch.service.BranchService;

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
@RequestMapping("branch")
@RequiredArgsConstructor
@Tag(name = "Branch Management", description = "Endpoints for managing branches")
public class BranchController {
    private final BranchService service;
    private final MessageSource messageSource;
    @PostMapping
    @Operation(summary = "Create a new branch", description = "Create and return a new branch")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created branch"),
            @ApiResponse(responseCode = "404", description = "Invalid input data"),
    })
    public ResponseEntity<GeneralBodyResponse<BranchEntity>> save(@Valid @RequestBody BranchCreateDTO dto, Locale locale) {
        BranchEntity save = service.save(dto, locale);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new GeneralBodyResponse<>(messageSource.getMessage("general.create.success", null, locale), save));
    }

    @PutMapping("/{branchId}/name")
    @Operation(summary = "Update branch's name", description = "Update the name of a branch given its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated branch name"),
            @ApiResponse(responseCode = "400", description = "Invalid name provided"),
            @ApiResponse(responseCode = "404", description = "Branch not found")
    })
    public ResponseEntity<GeneralBodyResponse<BranchEntity>> updateName(@PathVariable UUID branchId, @RequestBody BranchUpdateDTO dto, Locale locale) {
        BranchEntity save = service.updateName(branchId, dto.getName(), locale);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GeneralBodyResponse<>(messageSource.getMessage("general.create.success", null, locale), save));
    }

    @GetMapping
    @Operation(summary = "Retrieve all branches", description = "Return a paginated list of all branches")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved branches"),
    })
    public ResponseEntity<GeneralBodyResponse<Page<BranchEntity>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Locale locale) {

        Pageable pageable = PageRequest.of(page, size);
        Page<BranchEntity> all = service.getAll(pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GeneralBodyResponse<>(messageSource.getMessage("general.get.success", null, locale), all));
    }
}
