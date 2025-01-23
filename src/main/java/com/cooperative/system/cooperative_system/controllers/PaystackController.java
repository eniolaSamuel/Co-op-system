package com.cooperative.system.cooperative_system.controllers;


import com.cooperative.system.cooperative_system.dtos.requests.AddCardRequest;
import com.cooperative.system.cooperative_system.dtos.responses.PaystackTransactionResponse;
import com.cooperative.system.cooperative_system.exceptions.CoopException;
import com.cooperative.system.cooperative_system.services.interfaces.PaystackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/paystack")
public class PaystackController {

    @Autowired
    private PaystackService paystackService;

    @PostMapping("/{memberId}/add-card")
    public ResponseEntity<String> addCard(@PathVariable UUID memberId, @RequestBody AddCardRequest request) {
        return ResponseEntity.ok(paystackService.addCard(memberId, request));
    }

    @PostMapping("/deposit/{memberId}")
    public ResponseEntity<PaystackTransactionResponse> initiateDeposit(@PathVariable UUID memberId, @RequestParam BigDecimal amount) {
        PaystackTransactionResponse response = paystackService.initiateDeposit(memberId, amount);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/withdraw/{memberId}")
    public ResponseEntity<PaystackTransactionResponse> initiateWithdrawal(@PathVariable UUID memberId, @RequestParam BigDecimal amount) {
        PaystackTransactionResponse response = paystackService.initiateWithdrawal(memberId, amount);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/verify/{reference}")
    public ResponseEntity<Boolean> verifyTransaction(@PathVariable String reference) throws CoopException {
        boolean isVerified = paystackService.verifyTransaction(reference);
        return ResponseEntity.ok(isVerified);
    }
}


