package com.fridge.community_fridge_backend.service;

import com.fridge.community_fridge_backend.entity.*;
import com.fridge.community_fridge_backend.exception.ResourceNotFoundException;
import com.fridge.community_fridge_backend.repository.FoodItemRepository;
import com.fridge.community_fridge_backend.repository.PickupRequestRepository;
import com.fridge.community_fridge_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PickupRequestService {

    private final PickupRequestRepository pickupRequestRepository;
    private final FoodItemRepository foodItemRepository;
    private final UserRepository userRepository;

    // Create a pickup request
    public PickupRequest requestPickup(Long foodItemId) {
        FoodItem foodItem = foodItemRepository.findById(foodItemId)
                .orElseThrow(() -> new ResourceNotFoundException("FoodItem", "id", foodItemId));

        PickupRequest pickupRequest = PickupRequest.builder()
                .foodItem(foodItem)
                .status(PickupStatus.PENDING)
                .build();

        return pickupRequestRepository.save(pickupRequest);
    }

    // Volunteer accepts the pickup
    public PickupRequest acceptPickup(Long requestId, Long volunteerId) {
        PickupRequest request = pickupRequestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("PickupRequest", "id", requestId));

        User volunteer = userRepository.findById(volunteerId)
                .orElseThrow(() -> new ResourceNotFoundException("User (Volunteer)", "id", volunteerId));

        request.setVolunteer(volunteer);
        request.setStatus(PickupStatus.ACCEPTED);

        return pickupRequestRepository.save(request);
    }

    // Mark as delivered
    public PickupRequest markAsDelivered(Long requestId) {
        PickupRequest request = pickupRequestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("PickupRequest", "id", requestId));

        request.setStatus(PickupStatus.DELIVERED);
        request.setDeliveredAt(LocalDateTime.now());

        return pickupRequestRepository.save(request);
    }

    // Get pickups for a volunteer
    public List<PickupRequest> getPickupsForVolunteer(Long volunteerId) {
        User volunteer = userRepository.findById(volunteerId)
                .orElseThrow(() -> new ResourceNotFoundException("User (Volunteer)", "id", volunteerId));
        return pickupRequestRepository.findByVolunteer(volunteer);
    }

    // Get all pickup requests
    public List<PickupRequest> getAllPickupRequests() {
        return pickupRequestRepository.findAll();
    }
}
