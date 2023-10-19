package com.nequi.msfranchise.us_branch.service;

import com.nequi.msfranchise.entity.BranchEntity;
import com.nequi.msfranchise.entity.FranchiseEntity;
import com.nequi.msfranchise.exception.ModelNotFoundException;
import com.nequi.msfranchise.us_branch.dto.BranchCreateDTO;
import com.nequi.msfranchise.us_branch.repository.BranchRepository;
import com.nequi.msfranchise.us_franchise.repository.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.UUID;

/**
 * Service responsible for managing branch-related operations.
 */
@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;
    private final FranchiseRepository franchiseRepository;
    private final MessageSource messageSource;


    /**
     * Saves a new branch entity based on the provided DTO.
     *
     * @param dto    Data Transfer Object containing branch details.
     * @param locale Locale information for error messages.
     * @return Saved branch entity.
     * @throws ModelNotFoundException If related franchise is not found.
     */
    public BranchEntity save(BranchCreateDTO dto, Locale locale) {
        BranchEntity entity = new BranchEntity();
        // Buscando y validando la franquicia
        FranchiseEntity franchise = franchiseRepository.findById(dto.getFranchiseId())
                .orElseThrow(() -> {
                    String message = messageSource.getMessage("exception.model.notfound", null, locale);
                    return new ModelNotFoundException(message);
                });
        entity.setFranchise(franchise);
        entity.setName(dto.getName());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        entity.setAddress(dto.getAddress());
        return branchRepository.save(entity);
    }

    /**
     * Updates the name of an existing branch entity.
     *
     * @param branchId ID of the branch to be updated.
     * @param newName  New name for the branch.
     * @param locale   Locale information for error messages.
     * @return Updated branch entity.
     * @throws ModelNotFoundException If branch is not found.
     */
    public BranchEntity updateName(UUID branchId, String newName, Locale locale) {
        BranchEntity entity = branchRepository.findById(branchId).orElseThrow(() ->{
            String message = messageSource.getMessage("exception.model.notfound", null, locale);
            return new ModelNotFoundException(message);
        });
        entity.setName(newName);
        return branchRepository.save(entity);
    }

    public Page<BranchEntity> getAll(Pageable pageable) {
        return branchRepository.findAll(pageable);
    }
}
