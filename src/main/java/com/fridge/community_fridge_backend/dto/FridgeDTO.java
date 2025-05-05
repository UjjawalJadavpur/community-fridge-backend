package com.fridge.community_fridge_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FridgeDTO {

    private Long id;
    private String name;
    private String status;
    private String location;
    private Double latitude;
    private Double longitude;

    private List<FridgeItemDTO> fridgeItems; // optional, for full nested responses
}
