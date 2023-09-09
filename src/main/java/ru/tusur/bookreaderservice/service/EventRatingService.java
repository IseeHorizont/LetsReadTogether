package ru.tusur.bookreaderservice.service;


import ru.tusur.bookreaderservice.dto.EventRating;
import ru.tusur.bookreaderservice.entity.Vote;

import java.util.List;

public interface EventRatingService {

    EventRating getEventRatingById(long eventId);

    Vote addVote(Vote newVote);

    List<EventRating> getAllEventRating();
}
