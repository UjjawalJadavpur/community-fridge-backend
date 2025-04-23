package com.fridge.community_fridge_backend.repository;

import com.fridge.community_fridge_backend.entity.PickupRequest;
import com.fridge.community_fridge_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PickupRequestRepository extends JpaRepository<PickupRequest, Long> {
    List<PickupRequest> findByVolunteer(User volunteer);
}
