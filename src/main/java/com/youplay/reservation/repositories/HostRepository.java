package com.youplay.reservation.repositories;

import com.youplay.reservation.models.Host;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRepository extends CrudRepository<com.youplay.reservation.models.Host, Long> {
    List<Host> findAll();
}