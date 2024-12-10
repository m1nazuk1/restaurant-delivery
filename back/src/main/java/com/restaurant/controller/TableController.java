package com.restaurant.controller;

import com.restaurant.dto.TableDTO.TableRequestDTO;
import com.restaurant.dto.TableDTO.TableResponseDTO;
import com.restaurant.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class TableController {

    @Autowired
    private TableService tableService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TableResponseDTO createTable(@RequestBody TableRequestDTO tableRequestDTO) {
        return tableService.createTable(tableRequestDTO);
    }

    @GetMapping("/{id}")
    public TableResponseDTO getTable(@PathVariable Long id) {
        return tableService.getTable(id);
    }

    @GetMapping
    public List<TableResponseDTO> getAllTables() {
        return tableService.getAllTables();
    }

    @PutMapping("/{id}")
    public TableResponseDTO updateTable(@PathVariable Long id, @RequestBody TableRequestDTO dto) {
        return tableService.updateTable(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTable(@PathVariable Long id) {
        tableService.deleteTable(id);
    }
}