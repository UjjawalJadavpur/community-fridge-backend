package com.fridge.community_fridge_backend.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodItemDTO {

    private Long id;
    private String name;
    private int quantity;
    private LocalDateTime expiryDate;
    private String imageUrl;
    private Long donorId;

}
