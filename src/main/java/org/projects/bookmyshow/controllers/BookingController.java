package org.projects.bookmyshow.controllers;

import org.projects.bookmyshow.dtos.CreateBookingRequestDto;
import org.projects.bookmyshow.dtos.CreateBookingResponseDto;
import org.projects.bookmyshow.services.BookingService;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public CreateBookingResponseDto createBooking(CreateBookingRequestDto createBookingRequestDto) {
        return null;
    }
}
