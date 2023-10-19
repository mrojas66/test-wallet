package com.nequi.msfranchise.us_franchise.service;


import com.nequi.msfranchise.entity.FranchiseEntity;
import com.nequi.msfranchise.us_franchise.dto.FranchiseCreateDTO;
import com.nequi.msfranchise.us_franchise.repository.FranchiseRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


import java.util.Locale;

@Service
@RequiredArgsConstructor
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;

    public FranchiseEntity save(FranchiseCreateDTO dto) {
        FranchiseEntity entity = new FranchiseEntity();
        entity.setName(dto.getName());
        return franchiseRepository.save(entity);
    }


}
