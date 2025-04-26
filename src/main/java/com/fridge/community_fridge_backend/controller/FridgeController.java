package com.fridge.community_fridge_backend.controller;

import com.fridge.community_fridge_backend.dto.FridgeDTO;
import com.fridge.community_fridge_backend.service.FridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fridges")
public class FridgeController {

    private final FridgeService fridgeService;

    @Autowired
    public FridgeController(FridgeService fridgeService) {
        this.fridgeService = fridgeService;
    }

    @PostMapping
    public ResponseEntity<FridgeDTO> createFridge(@RequestBody FridgeDTO fridgeDTO) {
        FridgeDTO createdFridge = fridgeService.createFridge(fridgeDTO);
        return ResponseEntity.ok(createdFridge);
    }

    @GetMapping
    public ResponseEntity<List<FridgeDTO>> getAllFridges() {
        List<FridgeDTO> fridges = fridgeService.getAllFridges();
        return ResponseEntity.ok(fridges);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FridgeDTO> getFridgeById(@PathVariable Long id) {
        FridgeDTO fridge = fridgeService.getFridgeById(id);
        return ResponseEntity.ok(fridge);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFridge(@PathVariable Long id) {
        fridgeService.deleteFridge(id);
        return ResponseEntity.ok("Fridge deleted successfully.");
    }
}
