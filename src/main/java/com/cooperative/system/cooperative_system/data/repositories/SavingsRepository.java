package com.cooperative.system.cooperative_system.data.repositories;

import com.cooperative.system.cooperative_system.data.models.Savings;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SavingsRepository extends JpaRepository<Savings, UUID> {
    Optional<Savings> findByMemberId(UUID memberId);
}

