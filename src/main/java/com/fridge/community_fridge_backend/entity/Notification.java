package com.fridge.community_fridge_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipient;

    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private LocalDateTime sentAt;

    @PrePersist
    public void onCreate() {
        this.sentAt = LocalDateTime.now();
    }
}
