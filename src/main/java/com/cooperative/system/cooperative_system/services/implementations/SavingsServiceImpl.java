package com.cooperative.system.cooperative_system.services.implementations;

import com.cooperative.system.cooperative_system.data.models.Member;
import com.cooperative.system.cooperative_system.data.models.Savings;
import com.cooperative.system.cooperative_system.data.models.Transaction;
import com.cooperative.system.cooperative_system.data.repositories.MemberRepository;
import com.cooperative.system.cooperative_system.data.repositories.SavingsRepository;
import com.cooperative.system.cooperative_system.data.repositories.TransactionRepository;
import com.cooperative.system.cooperative_system.dtos.requests.DepositRequest;
import com.cooperative.system.cooperative_system.dtos.requests.WithdrawalRequest;
import com.cooperative.system.cooperative_system.dtos.responses.PaystackTransactionResponse;
import com.cooperative.system.cooperative_system.exceptions.CoopException;
import com.cooperative.system.cooperative_system.services.interfaces.PaystackService;
import com.cooperative.system.cooperative_system.services.interfaces.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SavingsServiceImpl implements SavingsService {

    @Autowired
    private SavingsRepository savingsRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PaystackService paystackService;

    private static final BigDecimal WITHDRAWAL_CHARGE_RATE = new BigDecimal("0.05");

    @Override
    @Transactional
    public Savings createSavingsAccount(UUID memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Savings savings = new Savings();
        savings.setMember(member);
        savings.setStartDate(LocalDate.now());
        savings.setBalance(BigDecimal.ZERO);

        return savingsRepository.save(savings);
    }

    @Override
    @Transactional
    public Transaction deposit(UUID memberId, DepositRequest request) throws CoopException {
        Savings savings = getSavingsAccount(memberId);
        PaystackTransactionResponse paystackResponse = paystackService.initiateDeposit(memberId, request.getAmount());

        if (paystackService.verifyTransaction(paystackResponse.getData().getReference())) {
            savings.setBalance(savings.getBalance().add(request.getAmount()));
            savings.setLastDepositDate(LocalDate.now());
            savingsRepository.save(savings);

            Transaction transaction = new Transaction();
            transaction.setMember(savings.getMember());
            transaction.setSavings(savings);
            transaction.setAmount(request.getAmount());
            transaction.setType("DEPOSIT");
            transaction.setTransactionDate(LocalDateTime.now());
            transaction.setPaystackReference(paystackResponse.getData().getReference());

            return transactionRepository.save(transaction);
        } else {
            throw new RuntimeException("Deposit transaction failed");
        }
    }

    @Override
    @Transactional
    public Transaction withdraw(UUID memberId, WithdrawalRequest request) throws CoopException {
        Savings savings = getSavingsAccount(memberId);
        BigDecimal withdrawalCharge = request.getAmount().multiply(WITHDRAWAL_CHARGE_RATE);
        BigDecimal totalWithdrawal = request.getAmount().add(withdrawalCharge);

        if (savings.getBalance().compareTo(totalWithdrawal) < 0) {
            throw new RuntimeException("Insufficient balance for withdrawal");
        }

        PaystackTransactionResponse paystackResponse = paystackService.initiateWithdrawal(memberId, request.getAmount());

        if (paystackService.verifyTransaction(paystackResponse.getData().getReference())) {
            savings.setBalance(savings.getBalance().subtract(totalWithdrawal));
            savings.setLastWithdrawalDate(LocalDate.now());
            savingsRepository.save(savings);

            Transaction transaction = new Transaction();
            transaction.setMember(savings.getMember());
            transaction.setSavings(savings);
            transaction.setAmount(request.getAmount());
            transaction.setType("WITHDRAWAL");
            transaction.setTransactionDate(LocalDateTime.now());
            transaction.setPaystackReference(paystackResponse.getData().getReference());
            transaction.setCharges(withdrawalCharge);

            return transactionRepository.save(transaction);
        } else {
            throw new RuntimeException("Withdrawal transaction failed");
        }
    }

    @Override
    public Savings getSavingsAccount(UUID memberId) {
        return savingsRepository.findByMemberId(memberId)
                .orElseThrow(() -> new RuntimeException("Savings account not found"));
    }

    @Override
    public List<Transaction> getTransactions(UUID memberId) {
        return transactionRepository.findByMemberId(memberId);
    }
}


