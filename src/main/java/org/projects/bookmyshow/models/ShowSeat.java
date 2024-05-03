package org.projects.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel {
    @ManyToOne
    private Show show;

    @ManyToOne
    private Seat seat;

    @Enumerated(EnumType.ORDINAL)
    private ShowSeatStatus showSeatStatus;
}

/*

   1          1
ShowSeat --- Show => M : 1
   M          1

Show - X, Y
Seat - A1, A2, ...
ShowSeat -
XA1, YA1
XA2, YA2
.
.

   1          1
ShowSeat --- Seat => M : 1
   M          1

*/