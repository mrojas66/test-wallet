package com.nequi.msfranchise.us_franchise.service;


import com.nequi.msfranchise.entity.FranchiseEntity;
import com.nequi.msfranchise.us_franchise.dto.FranchiseCreateDTO;
import com.nequi.msfranchise.us_franchise.repository.FranchiseRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;

    public FranchiseEntity save(FranchiseCreateDTO dto) {
        FranchiseEntity entity = new FranchiseEntity();
        entity.setName(dto.getName());
        return franchiseRepository.save(entity);
    }


    public FranchiseEntity updateName(UUID franchiseId, String newName) {
        FranchiseEntity entity = franchiseRepository.findById(franchiseId).orElseThrow();
        entity.setName(newName);
        return franchiseRepository.save(entity);
    }

    public Page<FranchiseEntity> getAll(Pageable pageable) {
        return franchiseRepository.findAll(pageable);
    }
}
