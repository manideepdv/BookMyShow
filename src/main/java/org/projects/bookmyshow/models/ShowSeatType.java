package org.projects.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class ShowSeatType extends BaseModel{
    @ManyToOne
    private Show show;

    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;

    private int price;
}

/*

Show - X, Y, Z
SeatType - GOLD, SILVER ...

ShowSeatType - X_GOLD, X_SILVER ...

    1             1
ShowSeatType --- Show => M : 1
    M             1


*/