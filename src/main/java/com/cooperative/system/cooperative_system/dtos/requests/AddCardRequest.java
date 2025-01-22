package com.cooperative.system.cooperative_system.dtos.requests;


import lombok.Data;

@Data
public class AddCardRequest {
    private String cardNumber;
    private String expiryMonth;
    private String expiryYear;
    private String cvv;
    private String pin;
}


