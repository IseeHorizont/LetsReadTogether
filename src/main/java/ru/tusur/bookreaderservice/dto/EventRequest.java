package ru.tusur.bookreaderservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
public class EventRequest {

    private String description;

    private String bookTitle;
    private String bookAuthor;
    private String bookPublicationYear;

    private String startDate;
    private String endDate;

    private String categoryName;

}
