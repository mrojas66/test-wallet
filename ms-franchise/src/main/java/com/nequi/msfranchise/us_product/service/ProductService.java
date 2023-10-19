package com.nequi.msfranchise.us_product.service;

import com.nequi.msfranchise.entity.BranchEntity;
import com.nequi.msfranchise.entity.ProductEntity;
import com.nequi.msfranchise.exception.ModelNotFoundException;
import com.nequi.msfranchise.us_branch.repository.BranchRepository;
import com.nequi.msfranchise.us_franchise.repository.FranchiseRepository;
import com.nequi.msfranchise.us_product.dto.ProductCreateDTO;
import com.nequi.msfranchise.us_product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * Service responsible for managing product-related operations.
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;
    private final FranchiseRepository franchiseRepository;
    private final MessageSource messageSource;

    /**
     * Saves a new product entity based on the provided DTO.
     *
     * @param dto    Data Transfer Object containing product details.
     * @param locale The locale information for error messages.
     * @return Saved product entity.
     */
    public ProductEntity save(ProductCreateDTO dto, Locale locale) {
        ProductEntity product = new ProductEntity();

        // Fetch branch by ID and set the relation
        BranchEntity branch = branchRepository.findById(dto.getBranchId())
                .orElseThrow(() -> {
                    String message = messageSource.getMessage("exception.branch.notfound", null, locale);
                    return new ModelNotFoundException(message);
                });

        product.setBranch(branch);
        product.setName(dto.getName());
        product.setUnitPrice(dto.getUnitPrice());
        product.setStock(dto.getStock());

        return productRepository.save(product);
    }

    /**
     * Updates the stock of an existing product.
     *
     * @param productId ID of the product to be updated.
     * @param newStock  New stock value.
     * @param locale    The locale information for error messages.
     * @return Updated product entity.
     */
    public ProductEntity updateStock(UUID productId, int newStock, Locale locale) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    String message = messageSource.getMessage("exception.product.notfound", null, locale);
                    return new ModelNotFoundException(message);
                });

        product.setStock(newStock);
        return productRepository.save(product);
    }

    /**
     * Fetches products with the highest stock associated with a specific franchise.
     *
     * @param franchiseId ID of the franchise.
     * @return List of top stock products.
     */
    public List<ProductEntity> getTopStockProductsByFranchise(UUID franchiseId, Locale locale) {
        franchiseRepository.findById(franchiseId).orElseThrow(() -> {
            String message = messageSource.getMessage("exception.franchise.notfound", null, locale);
            return new ModelNotFoundException(message);
        });
        return productRepository.findTopStockProductsByFranchise(franchiseId);
    }

    /**
     * Updates the name of an existing product.
     *
     * @param productId ID of the product to be updated.
     * @param newName   New name for the product.
     * @param locale    The locale information for error messages.
     * @return Updated product entity.
     */
    public ProductEntity updateName(UUID productId, String newName, Locale locale) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    String message = messageSource.getMessage("exception.product.notfound", null, locale);
                    return new ModelNotFoundException(message);
                });

        product.setName(newName);
        return productRepository.save(product);
    }

    /**
     * Fetches all products based on given pagination criteria.
     *
     * @param pageable Pagination information.
     * @return Page of product entities.
     */
    public Page<ProductEntity> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
