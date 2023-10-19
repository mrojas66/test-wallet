package com.nequi.msfranchise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.UUID;

@Entity
@Data
@Table(name = "nq_product")
public class ProductEntity extends Auditable {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name="branch_id", nullable=false)
    private BranchEntity branch;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "stock")
    private Integer stock;
}
