package com.cooperative.system.cooperative_system.services.interfaces;


import com.cooperative.system.cooperative_system.data.models.Savings;
import com.cooperative.system.cooperative_system.data.models.Transaction;
import com.cooperative.system.cooperative_system.dtos.requests.DepositRequest;
import com.cooperative.system.cooperative_system.dtos.requests.WithdrawalRequest;
import com.cooperative.system.cooperative_system.exceptions.CoopException;

import java.util.List;
import java.util.UUID;

public interface SavingsService {
    Savings createSavingsAccount(UUID memberId);
    Transaction deposit(UUID memberId, DepositRequest request) throws CoopException;
    Transaction withdraw(UUID memberId, WithdrawalRequest request) throws CoopException;
    Savings getSavingsAccount(UUID memberId);
    List<Transaction> getTransactions(UUID memberId);
}


