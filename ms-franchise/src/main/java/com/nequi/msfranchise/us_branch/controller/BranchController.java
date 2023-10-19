package com.nequi.msfranchise.us_branch.controller;

import com.nequi.msfranchise.commons.GeneralBodyResponse;
import com.nequi.msfranchise.entity.BranchEntity;
import com.nequi.msfranchise.us_branch.dto.BranchCreateDTO;
import com.nequi.msfranchise.us_branch.dto.BranchUpdateDTO;
import com.nequi.msfranchise.us_branch.service.BranchService;

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
public class BranchController {
    private final BranchService service;
    private final MessageSource messageSource;
    @PostMapping
    public ResponseEntity<GeneralBodyResponse<BranchEntity>> save(@Valid @RequestBody BranchCreateDTO dto, Locale locale) {
        BranchEntity save = service.save(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new GeneralBodyResponse<>(messageSource.getMessage("general.create.success", null, locale), save));
    }

    @PutMapping("/{branchId}/name")
    public ResponseEntity<GeneralBodyResponse<BranchEntity>> updateName(@PathVariable UUID branchId, @RequestBody BranchUpdateDTO dto, Locale locale) {
        BranchEntity save = service.updateName(branchId, dto.getName());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GeneralBodyResponse<>(messageSource.getMessage("general.create.success", null, locale), save));
    }

    @GetMapping
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
