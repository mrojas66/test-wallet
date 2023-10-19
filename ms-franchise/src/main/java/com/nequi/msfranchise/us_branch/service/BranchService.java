package com.nequi.msfranchise.us_branch.service;

import com.nequi.msfranchise.entity.BranchEntity;
import com.nequi.msfranchise.us_branch.dto.BranchCreateDTO;
import com.nequi.msfranchise.us_branch.repository.BranchRepository;
import com.nequi.msfranchise.us_franchise.repository.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;
    private final FranchiseRepository franchiseRepository;

    public BranchEntity save(BranchCreateDTO dto) {
        BranchEntity entity = new BranchEntity();
        entity.setFranchise(franchiseRepository.findById(dto.getFranchiseId()).orElseThrow());
        entity.setName(dto.getName());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        entity.setAddress(dto.getAddress());
        return branchRepository.save(entity);
    }
}