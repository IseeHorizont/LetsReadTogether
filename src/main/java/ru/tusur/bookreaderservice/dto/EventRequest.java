package ru.tusur.bookreaderservice.dto;

import lombok.Data;
import lombok.ToString;

@Data
public class EventRequest {

    private String description;

    private String bookTitle;
    private String bookAuthor;
    private String bookPublicationYear;

    private String startDate;
    private String endDate;

    private String categoryName;

}
