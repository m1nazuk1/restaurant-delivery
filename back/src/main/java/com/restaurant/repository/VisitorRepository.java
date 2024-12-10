package com.restaurant.repository;

import com.restaurant.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
}