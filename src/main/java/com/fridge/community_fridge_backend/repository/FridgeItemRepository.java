package com.fridge.community_fridge_backend.repository;

import com.fridge.community_fridge_backend.entity.FridgeItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FridgeItemRepository extends JpaRepository<FridgeItem, Long> {
    List<FridgeItem> findByFridgeId(Long fridgeId);
    List<FridgeItem> findByFoodItemId(Long foodItemId);
    List<FridgeItem> findByExpiredFalse();
    List<FridgeItem> findByExpiredTrue(); 
}
