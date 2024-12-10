package com.restaurant.repository;

import com.restaurant.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<Table, Long> {
    boolean existsByNumber(Integer number);
}