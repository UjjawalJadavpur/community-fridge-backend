package com.fridge.community_fridge_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FridgeItemDTO {

    private Long id;
    private Long fridgeId;
    private Long foodItemId;
    private int quantityLeft;
    private LocalDateTime addedAt;
}
