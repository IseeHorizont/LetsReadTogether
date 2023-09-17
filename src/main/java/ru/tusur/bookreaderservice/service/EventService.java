package ru.tusur.bookreaderservice.service;

import ru.tusur.bookreaderservice.entity.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();

    Event createEvent(String clientLogin, Event event);

    Event getEventById(Long eventId);

    Event updateEvent(String clientLogin, Long eventId, Event event);

    List<Event> getMostPopularEventsByRating(long eventsLimit);
}
