package com.youplay.reservation.services;

import com.youplay.reservation.models.Host;
import com.youplay.reservation.repositories.HostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostService {
    @Autowired
    private HostRepository hostRepository;

    public List<Host> getAllHosts() {
        return hostRepository.findAll();
    }


    public List<Host> saveAll(List<Host> hosts) {
        return (List<Host>) hostRepository.saveAll(hosts);
    }

    public void clearHostTable() {
        hostRepository.deleteAll();
    }
}
