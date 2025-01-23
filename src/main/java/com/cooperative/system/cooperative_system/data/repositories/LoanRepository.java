package com.cooperative.system.cooperative_system.data.repositories;

import com.cooperative.system.cooperative_system.data.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoanRepository extends JpaRepository<Loan, UUID> {
    List<Loan> findByMemberId(String memberId);
}

