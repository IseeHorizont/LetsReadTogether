package ru.tusur.bookreaderservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    private String errorMessage;
}
