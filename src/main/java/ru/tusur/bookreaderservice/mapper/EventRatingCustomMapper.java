package ru.tusur.bookreaderservice.mapper;

import lombok.extern.slf4j.Slf4j;
import ru.tusur.bookreaderservice.dto.EventRating;
import ru.tusur.bookreaderservice.dto.EventRatingResponse;

@Slf4j
public class EventRatingCustomMapper {

    public static EventRatingResponse eventRatingToEventRatingResponse(EventRating foundEventRating) {
        return EventRatingResponse.builder()
                .eventId(foundEventRating.getEventId())
                .likeCounter(foundEventRating.getLikeCounter())
                .dislikeCounter(foundEventRating.getDislikeCounter())
                .build();
    }


}
