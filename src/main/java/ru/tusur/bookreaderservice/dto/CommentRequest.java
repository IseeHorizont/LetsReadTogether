package ru.tusur.bookreaderservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {

    @Min(1)
    private Long eventId;

    @NotBlank
    private String username; // email

    @NotBlank
    private String text;
}
