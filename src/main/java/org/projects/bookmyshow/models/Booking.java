package org.projects.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel {
    private String bookingId;
    private User user;
    private Show show;
    private List<ShowSeat> showSeats;
    private int amount;
    private List<Payment> payments;
    private BookingStatus bookingStatus;
}
