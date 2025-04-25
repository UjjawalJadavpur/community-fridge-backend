package com.fridge.community_fridge_backend.controller;

import com.fridge.community_fridge_backend.dto.FridgeDTO;
import com.fridge.community_fridge_backend.service.FridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fridges")
public class FridgeController {

    private final FridgeService fridgeService;

    @Autowired
    public FridgeController(FridgeService fridgeService) {
        this.fridgeService = fridgeService;
    }

    @PostMapping
    public ResponseEntity<FridgeDTO> createFridge(@Valid @RequestBody FridgeDTO fridgeDTO) {
        FridgeDTO createdFridge = fridgeService.createFridge(fridgeDTO);
        return new ResponseEntity<>(createdFridge, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FridgeDTO>> getAllFridges() {
        return ResponseEntity.ok(fridgeService.getAllFridges());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FridgeDTO> getFridgeById(@PathVariable Long id) {
        return ResponseEntity.ok(fridgeService.getFridgeById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFridge(@PathVariable Long id) {
        fridgeService.deleteFridge(id);
        return ResponseEntity.noContent().build();
    }
}
