package com.nequi.msfranchise.us_franchise.controller;

import com.nequi.msfranchise.commons.GeneralBodyResponse;
import com.nequi.msfranchise.entity.FranchiseEntity;
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
@RequestMapping("franchise")
@RequiredArgsConstructor
public class FranchiseController {
    private final FranchiseService service;
    private final MessageSource messageSource;


    @PostMapping
    public ResponseEntity<GeneralBodyResponse> save(@Valid @RequestBody FranchiseCreateDTO dto, Locale locale) {
        FranchiseEntity save = service.save(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new GeneralBodyResponse<>(messageSource.getMessage("general.create.success", null, locale), save));
    }
}
