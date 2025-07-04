package com.fridge.community_fridge_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "fridges")
@Getter
@Setter
public class Fridge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    private String status; // ACTIVE, INACTIVE, etc.

    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @OneToMany(mappedBy = "fridge", cascade = CascadeType.ALL)
    private List<FridgeItem> fridgeItems;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
