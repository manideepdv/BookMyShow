package org.projects.bookmyshow.services;

import org.projects.bookmyshow.exceptions.ShowNotFoundException;
import org.projects.bookmyshow.exceptions.ShowSeatNotFoundException;
import org.projects.bookmyshow.exceptions.UserNotFoundException;
import org.projects.bookmyshow.models.*;
import org.projects.bookmyshow.repositories.BookingRepository;
import org.projects.bookmyshow.repositories.ShowRepository;
import org.projects.bookmyshow.repositories.ShowSeatRepository;
import org.projects.bookmyshow.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final ShowSeatRepository showSeatRepository;
    private final UserRepository userRepository;
    private final ShowRepository showRepository;
    private final PriceCalculator priceCalculator;
    private final BookingRepository bookingRepository;

    BookingService(UserRepository userRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository, PriceCalculator priceCalculator, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculator = priceCalculator;
        this.bookingRepository = bookingRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE) // This takes the lock over the database
    public Booking createBooking(Long userId, List<Long> showSeatIds, Long showId) throws ShowNotFoundException, UserNotFoundException, ShowSeatNotFoundException {
        /*
        1. Get the user obj from DB using userId
        2. Get the show with given showId
        3. Get the list of show seats with the given ids
        ------   Take the Lock  ---------
        4. Check if all the seats are available are not
            if not throw an exception
            if yes, mark the status of all the seats as blocked
        ------   Release the lock  --------
        5. Creating the Booking with PENDING status and save to DB
        6. Save the changes in the DB
        7. Return the booking obj
         */

        // 1. Get the user obj from DB using userId
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        User user = optionalUser.get();

        // 2. Get the show with given showId
        Optional<Show> optionalShow = showRepository.findById(showId);

        if(optionalShow.isEmpty()) {
            throw new ShowNotFoundException("Show not found");
        }

        Show show = optionalShow.get();

        // 3. Get the list of show seats with the given ids
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        // 4. Check if all the seats are available are not
        for (ShowSeat showSeat : showSeats) {
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new ShowSeatNotFoundException("Show Seat with id: " + showSeat.getId() + " not available");
            }
        }
        // mark the status of all the seats as blocked
        for(ShowSeat showSeat : showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
        }

        // 5. Creating the Booking with PENDING status and save to DB
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setCreatedAt(new Date());
        booking.setShowSeats(showSeats);
        booking.setAmount(priceCalculator.calculatePrice(show, showSeats));
        booking.setPayments(new ArrayList<>());
        // 6. Save the changes in the DB
        return bookingRepository.save(booking);
    }
}
