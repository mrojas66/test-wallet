package com.nequi.msfranchise.us_product.service;

import com.nequi.msfranchise.entity.BranchEntity;
import com.nequi.msfranchise.entity.ProductEntity;
import com.nequi.msfranchise.us_branch.repository.BranchRepository;
import com.nequi.msfranchise.us_product.dto.ProductCreateDTO;
import com.nequi.msfranchise.us_product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;


    public ProductEntity save(ProductCreateDTO dto) {
        ProductEntity product = new ProductEntity();

        // Encuentra la sucursal por ID y establece la relación
        BranchEntity branch = branchRepository.findById(dto.getBranchId())
                .orElseThrow(() -> new IllegalArgumentException("El branchId proporcionado no existe."));

        product.setBranch(branch);
        product.setName(dto.getName());
        product.setUnitPrice(dto.getUnitPrice());
        product.setStock(dto.getStock());

        return productRepository.save(product);
    }
    public ProductEntity updateStock(UUID productId, int newStock) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + productId));

        product.setStock(newStock);
        return productRepository.save(product);
    }

    public List<ProductEntity> getTopStockProductsByFranchise(UUID franchiseId) {
        return productRepository.findTopStockProductsByFranchise(franchiseId);
    }
}
