package com.restaurant.repository;

import com.restaurant.dto.BookingDTO.BookingResponseDTO;
import com.restaurant.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByTableIdAndStartTimeBetweenOrEndTimeBetween(
            Long tableId,
            LocalDateTime startTimeStart,
            LocalDateTime startTimeEnd,
            LocalDateTime endTimeStart,
            LocalDateTime endTimeEnd
    );

    List<Booking> findAllByVisitorId(Long visitorId);
}