package com.fridge.community_fridge_backend.controller;

import com.fridge.community_fridge_backend.entity.PickupRequest;
import com.fridge.community_fridge_backend.service.PickupRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pickups")
@RequiredArgsConstructor
public class PickupRequestController {

    private final PickupRequestService pickupRequestService;

    // Create a pickup request (linked to a food item)
    @PostMapping("/request/{foodItemId}")
    public ResponseEntity<PickupRequest> requestPickup(@PathVariable Long foodItemId) {
        PickupRequest pickupRequest = pickupRequestService.requestPickup(foodItemId);
        return new ResponseEntity<>(pickupRequest, HttpStatus.CREATED);  // Returning 201 for creation
    }

    // Volunteer accepts a pickup
    @PostMapping("/accept/{requestId}/volunteer/{volunteerId}")
    public ResponseEntity<PickupRequest> acceptPickup(@PathVariable Long requestId, @PathVariable Long volunteerId) {
        PickupRequest updated = pickupRequestService.acceptPickup(requestId, volunteerId);
        return ResponseEntity.ok(updated);  // Returning 200 for successful operation
    }

    // Mark as delivered
    @PostMapping("/deliver/{requestId}")
    public ResponseEntity<PickupRequest> markAsDelivered(@PathVariable Long requestId) {
        PickupRequest updated = pickupRequestService.markAsDelivered(requestId);
        return ResponseEntity.ok(updated);  // Returning 200 for successful operation
    }

    // Get all pickup requests
    @GetMapping
    public ResponseEntity<List<PickupRequest>> getAllPickupRequests() {
        List<PickupRequest> pickupRequests = pickupRequestService.getAllPickupRequests();
        return ResponseEntity.ok(pickupRequests);  // Returning 200 for successful fetch
    }

    // Get pickup requests for a volunteer
    @GetMapping("/volunteer/{volunteerId}")
    public ResponseEntity<List<PickupRequest>> getPickupsForVolunteer(@PathVariable Long volunteerId) {
        List<PickupRequest> pickups = pickupRequestService.getPickupsForVolunteer(volunteerId);
        return ResponseEntity.ok(pickups);  // Returning 200 for successful fetch
    }
}
