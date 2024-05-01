package com.youplay.reservation.repositories;

import com.youplay.reservation.models.Reservation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r WHERE r.status.id IN :statuses")
    List<Reservation> findByStatusIn(@Param("statuses") List<Long> statuses);

    List<Reservation> getByStatus_statusIDIn(List<Long> statuses);

    @Transactional
    @Modifying
    @Query("update Reservation e set e.status.id = 4 where e.id = ?1")
    Integer setIsDeletedStatusByID(Long id);

}
