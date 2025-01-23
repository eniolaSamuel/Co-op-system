package com.cooperative.system.cooperative_system.data.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String memberId;
    private String loanDescription;
    private BigDecimal loanAmount;
    private LocalDate dateRequested;
    private LocalDate dateApproved;
    private LocalDate dateRejected;
    private LocalDate dueDate;
    private int loanDuration;
    private String loanStatus;
    private BigDecimal repaymentAmount;

    @ElementCollection
    @CollectionTable(name = "loan_repayments", joinColumns = @JoinColumn(name = "loan_id"))
    private List<Repayment> repayments = new ArrayList<>();

    @Embeddable
    @Data
    public static class Repayment {
        private LocalDate date;
        private BigDecimal amount;
    }
}




