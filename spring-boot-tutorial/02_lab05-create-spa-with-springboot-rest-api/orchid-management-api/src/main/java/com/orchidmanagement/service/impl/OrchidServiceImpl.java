package com.orchidmanagement.service.impl;

import com.orchidmanagement.entity.Orchid;
import com.orchidmanagement.repository.OrchidRepository;
import com.orchidmanagement.service.OrchidService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrchidServiceImpl implements OrchidService {

    private final OrchidRepository orchidRepository;

    public OrchidServiceImpl(OrchidRepository orchidRepository) {
        this.orchidRepository = orchidRepository;
    }

    @Override
    public List<Orchid> findAll() {
        return orchidRepository.findAll();
    }

    @Override
    public Optional<Orchid> findById(Long id) {
        return orchidRepository.findById(id);
    }

    @Transactional
    @Override
    public Orchid save(Orchid orchid) {
        return orchidRepository.save(orchid);
    }
}
