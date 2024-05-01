package com.youplay.reservation.repositories;

import com.youplay.reservation.models.Platform;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatformRepository extends CrudRepository<Platform, Long> {
    List<Platform> findAll();
}
