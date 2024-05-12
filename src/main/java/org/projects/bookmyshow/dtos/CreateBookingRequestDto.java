package org.projects.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateBookingRequestDto {
    private Long userId;
    private Long showId; // can be skipped as we have showSeatIds
    private List<Long> showSeatIds;

}
