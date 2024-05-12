package org.projects.bookmyshow.services;

import org.projects.bookmyshow.models.Show;
import org.projects.bookmyshow.models.ShowSeat;
import org.projects.bookmyshow.models.ShowSeatType;
import org.projects.bookmyshow.repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculator {
    private ShowSeatTypeRepository showSeatTypeRepository;

    PriceCalculator(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public int calculatePrice(Show show, List<ShowSeat> showSeats) {
        int amount = 0;
        List<ShowSeatType> showSeatTypes =
                showSeatTypeRepository.findAllByShow(show);

        for (ShowSeat showSeat : showSeats) { // 10
            for (ShowSeatType showSeatType : showSeatTypes) { // 3
                if (showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())) {
                    amount += showSeatType.getPrice();
                    break;
                }
            }
        }

        return amount;
    }
}
