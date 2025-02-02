package com.cooperative.system.cooperative_system.data.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class Savings {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    private LocalDate startDate;
    private LocalDate lastWithdrawalDate;
    private LocalDate lastDepositDate;
    private BigDecimal balance;
    private String depositFrequency;
    private BigDecimal maintenanceCharges;


}

