package com.fridge.community_fridge_backend.repository;

import com.fridge.community_fridge_backend.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    // You can add custom queries here if needed in the future
}
