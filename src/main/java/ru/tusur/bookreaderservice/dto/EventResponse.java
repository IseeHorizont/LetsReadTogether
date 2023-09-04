package ru.tusur.bookreaderservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
public class EventResponse {

    private Long id;
    private String description;
    private String eventTitle;
    private String eventImage;

    private String categoryName;
    private String bookTitle;
    private String bookAuthor;
    private String bookPublicationYear;

    private String startDate;
    private String endDate;

    private String createdAt;

    private String creatorName;
    private String creatorEmail;
    private String avatar;
}
