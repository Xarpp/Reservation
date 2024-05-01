package com.youplay.reservation.services;

import com.youplay.reservation.models.Platform;
import com.youplay.reservation.repositories.PlatformRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PlatformService {
    @Autowired
    private PlatformRepository platformRepository;

    public List<Platform> getAllPlatforms() {
        return platformRepository.findAll();
    }

    @Transactional
    public Platform addNewPlatform(Platform newPlatform) {
        return platformRepository.save(newPlatform);
    }
}
