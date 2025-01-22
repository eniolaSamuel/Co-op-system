package com.cooperative.system.cooperative_system.dtos.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositRequest {
    private BigDecimal amount;
    private String currency;
}
