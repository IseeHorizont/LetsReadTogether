package ru.tusur.bookreaderservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventRatingResponse {

    private Long eventId;

    private Long likeCounter;

    private Long dislikeCounter;
}
