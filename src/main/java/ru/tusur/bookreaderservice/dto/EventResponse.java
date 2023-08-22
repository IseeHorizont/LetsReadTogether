package ru.tusur.bookreaderservice.dto;

import lombok.Data;
import lombok.ToString;

@Data
public class EventResponse {

    private Long id;
    private String description;

    private String categoryName;
    private String bookTitle;
    private String bookAuthor;
    private String bookPublicationYear;

    private String startDate;
    private String endDate;

    private String creatorName;
}
