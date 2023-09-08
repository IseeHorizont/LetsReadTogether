package ru.tusur.bookreaderservice.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventRating {

    private Long eventId;

    private Long likeCounter;

    private Long dislikeCounter;
}
