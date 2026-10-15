package com.orchidmanagement.controller;

import com.orchidmanagement.dto.request.CreateOrchidRequestDto;
import com.orchidmanagement.dto.request.UpdateOrchidRequestDto;
import com.orchidmanagement.entity.Orchid;
import com.orchidmanagement.exception.RecordNotFoundException;
import com.orchidmanagement.service.OrchidService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin
@Tag(name = "Orchids", description = "Orchid management endpoints")
@RestController
@RequestMapping("/orchids")
public class OrchidRestController {

    private final OrchidService orchidService;

    public OrchidRestController(OrchidService orchidService) {
        this.orchidService = orchidService;
    }

    @Operation(summary = "List all orchids")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Orchids found"),
            @ApiResponse(responseCode = "404", description = "No orchids exist", content = @Content)
    })
    @GetMapping
    public ResponseEntity<?> fetchOrchids() {
        List<Orchid> orchids = orchidService.findAll();
        if (orchids.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orchids);
    }

    @Operation(summary = "Create an orchid")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Orchid created"),
            @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> createOrchid(@Valid @RequestBody CreateOrchidRequestDto createOrchidRequestDto) {

        Orchid entityOrchid = new Orchid();
        BeanUtils.copyProperties(createOrchidRequestDto, entityOrchid);

        return new ResponseEntity<>(orchidService.save(entityOrchid), HttpStatus.CREATED);
    }

    @Operation(summary = "Update an orchid by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Orchid updated"),
            @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content),
            @ApiResponse(responseCode = "404", description = "Orchid not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrchid(@PathVariable Long id,
                                          @Valid @RequestBody UpdateOrchidRequestDto updateOrchidRequestDto) {

        Orchid entityOrchid = orchidService.findById(id)
                .orElseThrow(RecordNotFoundException::new);
        BeanUtils.copyProperties(updateOrchidRequestDto, entityOrchid);
        return new ResponseEntity<>(orchidService.save(entityOrchid), HttpStatus.OK);
    }
}
