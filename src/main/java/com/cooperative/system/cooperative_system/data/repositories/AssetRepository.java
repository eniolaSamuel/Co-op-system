package com.cooperative.system.cooperative_system.data.repositories;

import com.cooperative.system.cooperative_system.data.models.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, String> {
    List<Asset> findByMemberId(String memberId);
}

