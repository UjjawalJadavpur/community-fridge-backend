package com.fridge.community_fridge_backend.service;

import com.fridge.community_fridge_backend.dto.FoodItemDTO;
import com.fridge.community_fridge_backend.entity.FoodItem;
import com.fridge.community_fridge_backend.entity.User;
import com.fridge.community_fridge_backend.exception.ResourceNotFoundException;
import com.fridge.community_fridge_backend.repository.FoodItemRepository;
import com.fridge.community_fridge_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;
    private final UserRepository userRepository;

    @Autowired
    public FoodItemService(FoodItemRepository foodItemRepository, UserRepository userRepository) {
        this.foodItemRepository = foodItemRepository;
        this.userRepository = userRepository;
    }

    public FoodItemDTO createFoodItem(FoodItemDTO foodItemDTO) {
        User donor = userRepository.findById(foodItemDTO.getDonorId())
                .orElseThrow(() -> new ResourceNotFoundException("User (Donor)", "id", foodItemDTO.getDonorId()));

        FoodItem foodItem = new FoodItem();
        foodItem.setName(foodItemDTO.getName());
        foodItem.setQuantity(foodItemDTO.getQuantity());
        foodItem.setExpiryDate(foodItemDTO.getExpiryDate());
        foodItem.setImageUrl(foodItemDTO.getImageUrl());
        foodItem.setDonor(donor);

        FoodItem savedFoodItem = foodItemRepository.save(foodItem);
        foodItemDTO.setId(savedFoodItem.getId());
        return foodItemDTO;
    }

    public List<FoodItemDTO> getAllFoodItems() {
        return foodItemRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FoodItemDTO getFoodItemById(Long id) {
        return foodItemRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("FoodItem", "id", id));
    }

    public void deleteFoodItem(Long id) {
        if (!foodItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("FoodItem", "id", id);
        }
        foodItemRepository.deleteById(id);
    }

    private FoodItemDTO convertToDTO(FoodItem foodItem) {
        FoodItemDTO foodItemDTO = new FoodItemDTO();
        foodItemDTO.setId(foodItem.getId());
        foodItemDTO.setName(foodItem.getName());
        foodItemDTO.setQuantity(foodItem.getQuantity());
        foodItemDTO.setExpiryDate(foodItem.getExpiryDate());
        foodItemDTO.setImageUrl(foodItem.getImageUrl());
        foodItemDTO.setDonorId(foodItem.getDonor().getId());
        return foodItemDTO;
    }
}
