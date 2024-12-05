package com.restaurant.service;

import com.restaurant.dto.VisitorDTO.VisitorRequestDTO;
import com.restaurant.dto.VisitorDTO.VisitorResponseDTO;
import com.restaurant.entity.Visitor;
import com.restaurant.exception.EntityNotFoundException;
import com.restaurant.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitorService {

    @Autowired
    private VisitorRepository visitorRepository;

    public VisitorResponseDTO createVisitor(VisitorRequestDTO dto) {
        Visitor visitor = new Visitor();
        visitor.setName(dto.getName());
        visitor.setPhoneNumber(dto.getPhoneNumber());
        visitorRepository.save(visitor);
        return toResponseDTO(visitor);
    }

    public VisitorResponseDTO getVisitor(Long id) {
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Visitor not found"));
        return toResponseDTO(visitor);
    }

    public List<VisitorResponseDTO> getAllVisitors() {
        return visitorRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    private VisitorResponseDTO toResponseDTO(Visitor visitor) {
        VisitorResponseDTO dto = new VisitorResponseDTO();
        dto.setId(visitor.getId());
        dto.setName(visitor.getName());
        dto.setPhoneNumber(visitor.getPhoneNumber());
        return dto;
    }
}