package org.projects.bookmyshow.repositories;

import org.projects.bookmyshow.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    List<ShowSeat> findAllById(Iterable<Long> showSeatIds);

    ShowSeat save(ShowSeat showSeat);
    /*
        Update + Insert => Upsert
    */
}
