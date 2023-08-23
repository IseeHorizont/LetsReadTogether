package ru.tusur.bookreaderservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
public class EventRequest {

    @NotBlank
    private String description;

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
