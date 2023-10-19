package com.nequi.msfranchise.us_branch.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class BranchCreateDTO {
    private UUID franchiseId;
    private String name;
    private Double latitude;
    private Double longitude;
    private String address;
}
