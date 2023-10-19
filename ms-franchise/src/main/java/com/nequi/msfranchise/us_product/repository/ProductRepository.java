package com.nequi.msfranchise.us_product.repository;

import com.nequi.msfranchise.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    @Query("SELECT p FROM ProductEntity p WHERE p.branch.id IN (SELECT b.id FROM BranchEntity b WHERE b.franchise.id = :franchiseId) AND p.stock = (SELECT MAX(p2.stock) FROM ProductEntity p2 WHERE p2.branch.id = p.branch.id)")
    List<ProductEntity> findTopStockProductsByFranchise(UUID franchiseId);
}
