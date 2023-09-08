package ru.tusur.bookreaderservice.service;


import ru.tusur.bookreaderservice.dto.EventRating;

public interface EventRatingService {

    EventRating getEventRatingById(long eventId);
}
