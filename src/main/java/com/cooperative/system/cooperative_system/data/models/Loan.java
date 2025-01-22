package com.cooperative.system.cooperative_system.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Loan {
    @Id
    private String id;

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


}

