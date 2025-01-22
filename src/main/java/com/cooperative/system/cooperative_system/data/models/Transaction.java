package com.cooperative.system.cooperative_system.data.models;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "savings_id")
    private Savings savings;

    private BigDecimal amount;
    private String type;
    private LocalDateTime transactionDate;
    private String paystackReference;
    private BigDecimal charges;
}

