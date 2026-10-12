package com.orchidmanagement.controller;

import com.orchidmanagement.dto.request.CreateOrchidRequestDto;
import com.orchidmanagement.dto.request.UpdateOrchidRequestDto;
import com.orchidmanagement.entity.Orchid;
import com.orchidmanagement.exception.RecordNotFoundException;
import com.orchidmanagement.service.OrchidService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin
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

    @PostMapping
    public ResponseEntity<?> createOrchid(@Valid @RequestBody CreateOrchidRequestDto createOrchidRequestDto) {

        Orchid entityOrchid = new Orchid();
        BeanUtils.copyProperties(createOrchidRequestDto, entityOrchid);

        return new ResponseEntity<>(orchidService.save(entityOrchid), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrchid(@PathVariable Long id,
                                          @Valid @RequestBody UpdateOrchidRequestDto updateOrchidRequestDto) {

        Orchid entityOrchid = orchidService.findById(id)
                .orElseThrow(RecordNotFoundException::new);
        BeanUtils.copyProperties(updateOrchidRequestDto, entityOrchid);
        return new ResponseEntity<>(orchidService.save(entityOrchid), HttpStatus.OK);
    }
}
