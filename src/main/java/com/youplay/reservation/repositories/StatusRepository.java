package com.youplay.reservation.repositories;

import com.youplay.reservation.models.Status;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<Status, Long> {
}
