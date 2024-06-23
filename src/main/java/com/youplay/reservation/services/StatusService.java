package com.youplay.reservation.services;

import com.youplay.reservation.models.Status;
import com.youplay.reservation.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusService {
    @Autowired
    StatusRepository statusRepository;

    public Status getStatusById(Long id) {
        return statusRepository.findById(id).orElseThrow();

    }
}
