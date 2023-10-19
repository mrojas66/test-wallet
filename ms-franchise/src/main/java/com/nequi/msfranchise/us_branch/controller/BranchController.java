package com.nequi.msfranchise.us_branch.controller;

import com.nequi.msfranchise.commons.GeneralBodyResponse;
import com.nequi.msfranchise.entity.BranchEntity;
import com.nequi.msfranchise.entity.FranchiseEntity;
import com.nequi.msfranchise.us_branch.dto.BranchCreateDTO;
import com.nequi.msfranchise.us_branch.service.BranchService;
import com.nequi.msfranchise.us_franchise.dto.FranchiseCreateDTO;
import com.nequi.msfranchise.us_franchise.service.FranchiseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("branch")
@RequiredArgsConstructor
public class BranchController {
    private final BranchService service;
    private final MessageSource messageSource;
    @PostMapping
    public ResponseEntity<GeneralBodyResponse> save(@Valid @RequestBody BranchCreateDTO dto, Locale locale) {
        BranchEntity save = service.save(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new GeneralBodyResponse<>(messageSource.getMessage("general.create.success", null, locale), save));
    }
}
