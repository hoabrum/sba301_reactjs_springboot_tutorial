package com.orchidmanagement.service;

import com.orchidmanagement.entity.Orchid;

import java.util.List;
import java.util.Optional;

public interface OrchidService {

    List<Orchid> findAll();

    Optional<Orchid> findById(Long id);

    Orchid save(Orchid orchid);

}
