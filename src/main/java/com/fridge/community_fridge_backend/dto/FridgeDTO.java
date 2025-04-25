package com.fridge.community_fridge_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FridgeDTO {

    private Long id;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String status;

    private List<FridgeItemDTO> fridgeItems; // optional, for full nested responses
}
