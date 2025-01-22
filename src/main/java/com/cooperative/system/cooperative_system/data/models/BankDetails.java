package com.cooperative.system.cooperative_system.data.models;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class BankDetails {
    private String accountName;
    private String accountNumber;
    private String bankName;

}

