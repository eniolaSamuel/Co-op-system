package com.cooperative.system.cooperative_system.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Savings {
    @Id
    private String id;

    private String memberId;
    private LocalDate startDate;
    private LocalDate lastWithdrawalDate;
    private LocalDate lastDepositDate;
    private BigDecimal totalDeposits;
    private BigDecimal totalWithdrawals;
    private String depositFrequency;
    private BigDecimal maintenanceCharges;


}

