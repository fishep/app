package com.fishep.user.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TestRequest {
    @NotNull(message = "id is null")
    @Min(value = 1, message = "id must large than 1")
    public Long id;
}
