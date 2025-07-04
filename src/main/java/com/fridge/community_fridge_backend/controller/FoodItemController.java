package com.fridge.community_fridge_backend.controller;

import com.fridge.community_fridge_backend.dto.FoodItemDTO;
import com.fridge.community_fridge_backend.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/food-items")
public class FoodItemController {

    private static final Logger log = LoggerFactory.getLogger(FoodItemController.class);

    private final FoodItemService foodItemService;

    @Autowired
    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @PostMapping
    public ResponseEntity<FoodItemDTO> createFoodItem(@Valid @RequestBody FoodItemDTO foodItemDTO) {
        log.info("Creating food item: " + foodItemDTO);
        FoodItemDTO createdFoodItem = foodItemService.createFoodItem(foodItemDTO);
        return new ResponseEntity<>(createdFoodItem, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FoodItemDTO>> getAllFoodItems() {
        return ResponseEntity.ok(foodItemService.getAllFoodItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodItemDTO> getFoodItemById(@PathVariable Long id) {
        FoodItemDTO foodItem = foodItemService.getFoodItemById(id);
        return ResponseEntity.ok(foodItem);
    }

    @GetMapping("/donor/{donorId}")
    public ResponseEntity<List<FoodItemDTO>> getFoodItemsByDonor(@PathVariable Long donorId) {
        List<FoodItemDTO> items = foodItemService.getFoodItemsByDonorId(donorId);
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoodItem(@PathVariable Long id) {
        foodItemService.deleteFoodItem(id);
        return ResponseEntity.noContent().build();
    }

    
}
