package com.orchidmanagement.repository;

import com.orchidmanagement.entity.Orchid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrchidRepository extends JpaRepository<Orchid, Long> {
}
