package com.cooperative.system.cooperative_system.data.repositories;


import com.cooperative.system.cooperative_system.data.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByMemberId(UUID memberId);
    List<Transaction> findBySavingsId(String savingsId);
    List<Transaction> findByType(String type); // E.g., "deposit" or "withdrawal"
}

