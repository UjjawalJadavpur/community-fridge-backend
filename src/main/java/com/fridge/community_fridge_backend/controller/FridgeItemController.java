package com.fridge.community_fridge_backend.controller;

import com.fridge.community_fridge_backend.dto.FridgeItemDTO;
import com.fridge.community_fridge_backend.service.FridgeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fridge-items")
public class FridgeItemController {

    private final FridgeItemService fridgeItemService;

    @Autowired
    public FridgeItemController(FridgeItemService fridgeItemService) {
        this.fridgeItemService = fridgeItemService;
    }

    @PostMapping
    public ResponseEntity<FridgeItemDTO> createFridgeItem(@Valid @RequestBody FridgeItemDTO dto) {
        FridgeItemDTO created = fridgeItemService.createFridgeItem(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/fridge/{fridgeId}")
    public ResponseEntity<List<FridgeItemDTO>> getItemsByFridge(@PathVariable Long fridgeId) {
        List<FridgeItemDTO> items = fridgeItemService.getItemsByFridgeId(fridgeId);
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFridgeItem(@PathVariable Long id) {
        fridgeItemService.deleteFridgeItem(id);
        return ResponseEntity.noContent().build();
    }
}
