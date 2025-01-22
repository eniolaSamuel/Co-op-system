package com.cooperative.system.cooperative_system.data.repositories;

import com.cooperative.system.cooperative_system.data.models.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavingsRepository extends JpaRepository<Savings, String> {
    List<Savings> findByMemberId(String memberId);
}

