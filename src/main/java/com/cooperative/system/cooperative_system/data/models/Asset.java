package com.cooperative.system.cooperative_system.data.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Asset {
    @Id
    private String id;

    private String memberId;
    private String assetName;
    private String assetDescription;
    private BigDecimal assetPrice;
    private BigDecimal amountPaid;
    private String paymentStatus;

    @ElementCollection
    private List<BigDecimal> installmentPayments;


}
