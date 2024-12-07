package com.restaurant.service;

import com.restaurant.dto.BookingDTO.BookingRequestDTO;
import com.restaurant.dto.BookingDTO.BookingResponseDTO;
import com.restaurant.entity.Table;
import com.restaurant.entity.Visitor;
import com.restaurant.exception.EntityNotFoundException;
import com.restaurant.entity.Booking;
import com.restaurant.repository.BookingRepository;
import com.restaurant.repository.TableRepository;
import com.restaurant.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private VisitorRepository visitorRepository;

    public List<BookingResponseDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public BookingResponseDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        return toResponseDTO(booking);
    }

    public BookingResponseDTO createBooking(BookingRequestDTO bookingRequestDTO) {
        Booking booking = new Booking();
        booking.setStartTime(bookingRequestDTO.getStartTime());
        booking.setEndTime(bookingRequestDTO.getEndTime());

        Table table = tableRepository.findById(bookingRequestDTO.getTableId())
                .orElseThrow(() -> new EntityNotFoundException("Table not found"));
        Visitor visitor = visitorRepository.findById(bookingRequestDTO.getVisitorId())
                .orElseThrow(() -> new EntityNotFoundException("Visitor not found"));

        booking.setTable(table);
        booking.setVisitor(visitor);

        booking = bookingRepository.save(booking);
        return toResponseDTO(booking);
    }

    private BookingResponseDTO toResponseDTO(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setId(booking.getId());
        dto.setStartTime(booking.getStartTime());
        dto.setEndTime(booking.getEndTime());

        dto.setTableId(booking.getTable().getId());
        dto.setVisitorName(booking.getVisitor().getName());
        dto.setVisitorPhoneNumber(booking.getVisitor().getPhoneNumber());

        return dto;
    }
}