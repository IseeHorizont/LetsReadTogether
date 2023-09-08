package ru.tusur.bookreaderservice.service;


import ru.tusur.bookreaderservice.dto.EventRating;
import ru.tusur.bookreaderservice.entity.Vote;

public interface EventRatingService {

    EventRating getEventRatingById(long eventId);

    Vote addVote(Vote newVote);
}
