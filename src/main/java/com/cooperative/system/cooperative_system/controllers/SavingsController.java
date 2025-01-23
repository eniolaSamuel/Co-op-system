package com.cooperative.system.cooperative_system.controllers;

import com.cooperative.system.cooperative_system.data.models.Savings;
import com.cooperative.system.cooperative_system.data.models.Transaction;
import com.cooperative.system.cooperative_system.dtos.requests.DepositRequest;
import com.cooperative.system.cooperative_system.dtos.requests.WithdrawalRequest;
import com.cooperative.system.cooperative_system.exceptions.CoopException;
import com.cooperative.system.cooperative_system.services.interfaces.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/savings")
public class SavingsController {

    @Autowired
    private SavingsService savingsService;

    @PostMapping("/{memberId}")
    public ResponseEntity<Savings> createSavingsAccount(@PathVariable UUID memberId) {
        return ResponseEntity.ok(savingsService.createSavingsAccount(memberId));
    }

    @PostMapping("/{memberId}/deposit")
    public ResponseEntity<Transaction> deposit(@PathVariable UUID memberId, @RequestBody DepositRequest request) throws CoopException {
        return ResponseEntity.ok(savingsService.deposit(memberId, request));
    }

    @PostMapping("/{memberId}/withdraw")
    public ResponseEntity<Transaction> withdraw(@PathVariable UUID memberId, @RequestBody WithdrawalRequest request) throws CoopException {
        return ResponseEntity.ok(savingsService.withdraw(memberId, request));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Savings> getSavingsAccount(@PathVariable UUID memberId) {
        return ResponseEntity.ok(savingsService.getSavingsAccount(memberId));
    }

    @GetMapping("/{memberId}/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable UUID memberId) {
        return ResponseEntity.ok(savingsService.getTransactions(memberId));
    }
}

