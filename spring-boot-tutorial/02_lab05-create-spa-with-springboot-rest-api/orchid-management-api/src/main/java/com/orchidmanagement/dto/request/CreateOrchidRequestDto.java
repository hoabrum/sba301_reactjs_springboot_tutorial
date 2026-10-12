package com.orchidmanagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateOrchidRequestDto {

    @NotNull
    private String orchidName;
    private boolean isNatural;

    private String image;
}
