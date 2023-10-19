package com.nequi.msfranchise.us_franchise.service;

import com.nequi.msfranchise.entity.FranchiseEntity;
import com.nequi.msfranchise.exception.ModelNotFoundException;
import com.nequi.msfranchise.us_franchise.dto.FranchiseCreateDTO;
import com.nequi.msfranchise.us_franchise.repository.FranchiseRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.UUID;

/**
 * Service responsible for managing franchise-related operations.
 */
@Service
@RequiredArgsConstructor
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;
    private final MessageSource messageSource;

    /**
     * Saves a new franchise entity based on the provided DTO.
     *
     * @param dto Data Transfer Object containing franchise details.
     * @return Saved franchise entity.
     */
    public FranchiseEntity save(FranchiseCreateDTO dto) {
        FranchiseEntity entity = new FranchiseEntity();
        entity.setName(dto.getName());
        return franchiseRepository.save(entity);
    }

    /**
     * Updates the name of an existing franchise entity.
     *
     * @param franchiseId ID of the franchise to be updated.
     * @param newName     New name for the franchise.
     * @param locale      The locale information for error messages.
     * @return Updated franchise entity.
     * @throws ModelNotFoundException If franchise is not found.
     */
    public FranchiseEntity updateName(UUID franchiseId, String newName, Locale locale) {
        FranchiseEntity entity = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> {
                    String message = messageSource.getMessage("exception.model.notfound", null, locale);
                    return new ModelNotFoundException(message);
                });

        entity.setName(newName);
        return franchiseRepository.save(entity);
    }

    /**
     * Fetches all franchises based on given pagination criteria.
     *
     * @param pageable Pagination information.
     * @return Page of franchise entities.
     */
    public Page<FranchiseEntity> getAll(Pageable pageable) {
        return franchiseRepository.findAll(pageable);
    }
}
