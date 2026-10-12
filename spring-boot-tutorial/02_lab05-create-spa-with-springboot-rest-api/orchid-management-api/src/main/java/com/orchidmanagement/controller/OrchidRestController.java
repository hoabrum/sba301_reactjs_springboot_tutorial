package com.orchidmanagement.controller;

import com.orchidmanagement.entity.Orchid;
import com.orchidmanagement.service.OrchidService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orchids")
public class OrchidRestController {

    private final OrchidService orchidService;

    public OrchidRestController(OrchidService orchidService) {
        this.orchidService = orchidService;
    }

    @GetMapping
    public ResponseEntity<?> fetchOrchids() {
        List<Orchid> orchids = orchidService.findAll();
        if (orchids.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orchids);
    }

}
