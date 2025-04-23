package com.fridge.community_fridge_backend.controller;

import com.fridge.community_fridge_backend.entity.PickupRequest;
import com.fridge.community_fridge_backend.service.PickupRequestService;
import lombok.RequiredArgsConstructor;
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
        return ResponseEntity.ok(pickupRequest);
    }

    // Volunteer accepts a pickup
    @PostMapping("/accept/{requestId}/volunteer/{volunteerId}")
    public ResponseEntity<PickupRequest> acceptPickup(@PathVariable Long requestId, @PathVariable Long volunteerId) {
        PickupRequest updated = pickupRequestService.acceptPickup(requestId, volunteerId);
        return ResponseEntity.ok(updated);
    }

    // Mark as delivered
    @PostMapping("/deliver/{requestId}")
    public ResponseEntity<PickupRequest> markAsDelivered(@PathVariable Long requestId) {
        PickupRequest updated = pickupRequestService.markAsDelivered(requestId);
        return ResponseEntity.ok(updated);
    }

    // Get all pickup requests
    @GetMapping
    public ResponseEntity<List<PickupRequest>> getAllPickupRequests() {
        return ResponseEntity.ok(pickupRequestService.getAllPickupRequests());
    }

    // Get pickup requests for a volunteer
    @GetMapping("/volunteer/{volunteerId}")
    public ResponseEntity<List<PickupRequest>> getPickupsForVolunteer(@PathVariable Long volunteerId) {
        return ResponseEntity.ok(pickupRequestService.getPickupsForVolunteer(volunteerId));
    }
}
