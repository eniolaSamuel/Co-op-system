package com.cooperative.system.cooperative_system.dtos.requests;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawalRequest {
    private BigDecimal amount;
    private String currency;
    private String accountNumber;
    private String bankCode;
}


