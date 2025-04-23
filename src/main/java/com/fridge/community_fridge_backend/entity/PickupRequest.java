package com.fridge.community_fridge_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pickup_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PickupRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "food_item_id", nullable = false)
    private FoodItem foodItem;

    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private User volunteer;

    @Enumerated(EnumType.STRING)
    private PickupStatus status = PickupStatus.PENDING;

    private LocalDateTime requestedAt;

    private LocalDateTime deliveredAt;

    @PrePersist
    public void onCreate() {
        this.requestedAt = LocalDateTime.now();
    }
}
