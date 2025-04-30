package com.fridge.community_fridge_backend.service;

import com.fridge.community_fridge_backend.dto.FridgeDTO;
import com.fridge.community_fridge_backend.entity.Fridge;
import com.fridge.community_fridge_backend.exception.ResourceNotFoundException;
import com.fridge.community_fridge_backend.repository.FridgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FridgeService {

    private final FridgeRepository fridgeRepository;

    @Autowired
    public FridgeService(FridgeRepository fridgeRepository) {
        this.fridgeRepository = fridgeRepository;
    }

    public FridgeDTO createFridge(FridgeDTO dto) {
        Fridge fridge = new Fridge();
        fridge.setName(dto.getName()); 
        fridge.setAddress(dto.getAddress());
        fridge.setLatitude(dto.getLatitude());
        fridge.setLongitude(dto.getLongitude());
        fridge.setStatus(dto.getStatus());

        Fridge saved = fridgeRepository.save(fridge);
        dto.setId(saved.getId());
        return dto;
    }

    public List<FridgeDTO> getAllFridges() {
        return fridgeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FridgeDTO getFridgeById(Long id) {
        Fridge fridge = fridgeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fridge", "id", id));
        return convertToDTO(fridge);
    }

    public void deleteFridge(Long id) {
        if (!fridgeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Fridge", "id", id);
        }
        fridgeRepository.deleteById(id);
    }

    private FridgeDTO convertToDTO(Fridge fridge) {
        FridgeDTO dto = new FridgeDTO();
        dto.setId(fridge.getId());
        dto.setName(fridge.getName()); 
        dto.setAddress(fridge.getAddress());
        dto.setLatitude(fridge.getLatitude());
        dto.setLongitude(fridge.getLongitude());
        dto.setStatus(fridge.getStatus());
        return dto;
    }
}
