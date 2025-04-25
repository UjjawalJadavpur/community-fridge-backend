package com.fridge.community_fridge_backend.repository;

import com.fridge.community_fridge_backend.entity.Fridge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FridgeRepository extends JpaRepository<Fridge, Long> {
    // Add custom queries if needed
}
