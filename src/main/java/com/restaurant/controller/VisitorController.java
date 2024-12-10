package com.restaurant.controller;

import com.restaurant.dto.VisitorDTO.VisitorRequestDTO;
import com.restaurant.dto.VisitorDTO.VisitorResponseDTO;
import com.restaurant.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitors")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VisitorResponseDTO createVisitor(@RequestBody VisitorRequestDTO visitorRequestDTO) {
        return visitorService.createVisitor(visitorRequestDTO);
    }

    @GetMapping("/{id}")
    public VisitorResponseDTO getVisitor(@PathVariable Long id) {
        return visitorService.getVisitor(id);
    }

    @GetMapping
    public List<VisitorResponseDTO> getAllVisitors() {
        return visitorService.getAllVisitors();
    }


    @PutMapping("/{id}")
    public VisitorResponseDTO updateVisitor(@PathVariable Long id, @RequestBody VisitorRequestDTO dto) {
        return visitorService.updateVisitor(dto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteVisitor(@PathVariable Long id) {
        visitorService.deleteVisitor(id);
    }
}