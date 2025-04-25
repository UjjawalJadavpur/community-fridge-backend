package com.fridge.community_fridge_backend.service;

import com.fridge.community_fridge_backend.dto.FridgeItemDTO;
import com.fridge.community_fridge_backend.entity.FoodItem;
import com.fridge.community_fridge_backend.entity.Fridge;
import com.fridge.community_fridge_backend.entity.FridgeItem;
import com.fridge.community_fridge_backend.exception.ResourceNotFoundException;
import com.fridge.community_fridge_backend.repository.FoodItemRepository;
import com.fridge.community_fridge_backend.repository.FridgeItemRepository;
import com.fridge.community_fridge_backend.repository.FridgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FridgeItemService {

    private final FridgeItemRepository fridgeItemRepository;
    private final FridgeRepository fridgeRepository;
    private final FoodItemRepository foodItemRepository;

    @Autowired
    public FridgeItemService(
            FridgeItemRepository fridgeItemRepository,
            FridgeRepository fridgeRepository,
            FoodItemRepository foodItemRepository
    ) {
        this.fridgeItemRepository = fridgeItemRepository;
        this.fridgeRepository = fridgeRepository;
        this.foodItemRepository = foodItemRepository;
    }

    public FridgeItemDTO createFridgeItem(FridgeItemDTO dto) {
        Fridge fridge = fridgeRepository.findById(dto.getFridgeId())
                .orElseThrow(() -> new ResourceNotFoundException("Fridge", "id", dto.getFridgeId()));

        FoodItem foodItem = foodItemRepository.findById(dto.getFoodItemId())
                .orElseThrow(() -> new ResourceNotFoundException("FoodItem", "id", dto.getFoodItemId()));

        FridgeItem fridgeItem = new FridgeItem();
        fridgeItem.setFridge(fridge);
        fridgeItem.setFoodItem(foodItem);

        FridgeItem saved = fridgeItemRepository.save(fridgeItem);
        dto.setId(saved.getId());
        return dto;
    }

    public List<FridgeItemDTO> getItemsByFridgeId(Long fridgeId) {
        return fridgeItemRepository.findByFridgeId(fridgeId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteFridgeItem(Long id) {
        if (!fridgeItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("FridgeItem", "id", id);
        }
        fridgeItemRepository.deleteById(id);
    }

    private FridgeItemDTO convertToDTO(FridgeItem fridgeItem) {
        FridgeItemDTO dto = new FridgeItemDTO();
        dto.setId(fridgeItem.getId());
        dto.setFridgeId(fridgeItem.getFridge().getId());
        dto.setFoodItemId(fridgeItem.getFoodItem().getId());
        return dto;
    }
}
