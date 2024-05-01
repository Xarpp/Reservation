package com.youplay.reservation.services;

import com.youplay.reservation.models.Host;
import com.youplay.reservation.repositories.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HostService {
    @Autowired
    private HostRepository hostRepository;

    public List<Host> getAllHosts() {
        return hostRepository.findAll();
    }

}
