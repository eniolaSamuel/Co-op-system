package com.cooperative.system.cooperative_system.data.repositories;

import com.cooperative.system.cooperative_system.data.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {
    List<Loan> findByMemberId(String memberId);
}

