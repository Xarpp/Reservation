package com.youplay.reservation.repositories;

import com.youplay.reservation.models.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r WHERE r.status.id IN :statuses")
    List<Reservation> findByStatusIn(@Param("statuses") List<Long> statuses);

}
