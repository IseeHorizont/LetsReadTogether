package ru.tusur.bookreaderservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {

    @NotBlank
    private String eventTitle;

    @NotBlank
    private String description;

    private String eventImage;

    @NotBlank
    private String bookTitle;

    @NotBlank
    private String bookAuthor;

    @NotBlank
    private String bookPublicationYear;

    private String startDate;
    private String endDate;

    @NotBlank
    private String categoryName;

}
