package com.cooperative.system.cooperative_system.services.interfaces;



import com.cooperative.system.cooperative_system.dtos.requests.AddCardRequest;
import com.cooperative.system.cooperative_system.dtos.responses.PaystackTransactionResponse;
import com.cooperative.system.cooperative_system.exceptions.CoopException;

import java.math.BigDecimal;
import java.util.UUID;

public interface PaystackService {
    String addCard(UUID memberId, AddCardRequest request);
    PaystackTransactionResponse initiateDeposit(UUID memberId, BigDecimal amount);
    PaystackTransactionResponse initiateWithdrawal(UUID memberId, BigDecimal amount);
    boolean verifyTransaction(String reference)throws CoopException;
}


