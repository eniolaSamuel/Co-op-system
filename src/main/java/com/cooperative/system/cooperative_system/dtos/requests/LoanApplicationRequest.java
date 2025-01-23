package com.cooperative.system.cooperative_system.dtos.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanApplicationRequest {
    private String loanDescription;
    private BigDecimal loanAmount;
    private int loanDuration;
    private String assetPurchased;
    private BigDecimal assetPrice;
}


