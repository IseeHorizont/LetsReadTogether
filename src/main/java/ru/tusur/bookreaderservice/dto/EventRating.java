package ru.tusur.bookreaderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventRating {

    private Long eventId;

    private Long likeCounter;

    private Long dislikeCounter;
}
