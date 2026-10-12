package com.orchidmanagement.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateOrchidRequestDto {

    @NotNull
    private Long id;

    @NotNull
    private String orchidName;

    private boolean isNatural;

    private String image;
}
