package com.restaurant.service;

import com.restaurant.dto.TableDTO.TableRequestDTO;
import com.restaurant.dto.TableDTO.TableResponseDTO;
import com.restaurant.dto.VisitorDTO.VisitorRequestDTO;
import com.restaurant.dto.VisitorDTO.VisitorResponseDTO;
import com.restaurant.entity.Table;
import com.restaurant.entity.Visitor;
import com.restaurant.exception.EntityNotFoundException;
import com.restaurant.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    public TableResponseDTO createTable(TableRequestDTO dto) {
        Table table = new Table();
        table.setNumber(dto.getNumber());
        table.setDescription(dto.getDescription());
        table.setStatus(dto.getStatus());
        tableRepository.save(table);
        return toResponseDTO(table);
    }


    public TableResponseDTO updateTable(TableRequestDTO dto, Long id) {
        Table table = tableRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Table not found"));

        table.setDescription(dto.getDescription());
        table.setStatus(dto.getStatus());
        table.setNumber(dto.getNumber());
        table = tableRepository.save(table);
        return toResponseDTO(table);
    }

    public TableResponseDTO getTable(Long id) {
        Table table = tableRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Table not found"));
        return toResponseDTO(table);
    }

    public List<TableResponseDTO> getAllTables() {
        return tableRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void deleteTable(Long id) {
        Table table = tableRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Table not found"));
        tableRepository.delete(table);
    }

    private TableResponseDTO toResponseDTO(Table table) {
        TableResponseDTO dto = new TableResponseDTO();
        dto.setId(table.getId());
        dto.setNumber(table.getNumber());
        dto.setDescription(table.getDescription());
        dto.setStatus(table.getStatus());
        return dto;
    }
}