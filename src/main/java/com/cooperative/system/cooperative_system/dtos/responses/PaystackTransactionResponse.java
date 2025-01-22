package com.cooperative.system.cooperative_system.dtos.responses;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PaystackTransactionResponse {
    private boolean status;
    private String message;
    private Data data;

    @lombok.Data
    public static class Data {
        private String reference;
        private String status;
        @JsonProperty("authorization_url")
        private String authorizationUrl;
        private Customer customer;
    }

    @lombok.Data
    public static class Customer {
        private String id;
        @JsonProperty("first_name")
        private String firstName;
        @JsonProperty("last_name")
        private String lastName;
        private String email;
        @JsonProperty("customer_code")
        private String customerCode;
        private String phone;
    }
}


