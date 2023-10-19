package com.nequi.msfranchise.us_franchise.repository;

import com.nequi.msfranchise.entity.BranchEntity;
import com.nequi.msfranchise.entity.FranchiseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FranchiseRepository extends JpaRepository<FranchiseEntity, UUID> {
}
