package org.projects.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateBookingResponseDto {
    private Long bookingId;
    private ResponseStatus responseStatus;
}
