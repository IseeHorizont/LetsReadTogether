package ru.tusur.bookreaderservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tusur.bookreaderservice.dto.EventRequest;
import ru.tusur.bookreaderservice.dto.EventResponse;
import ru.tusur.bookreaderservice.entity.Event;
import ru.tusur.bookreaderservice.mapper.EventCustomMapper;
import ru.tusur.bookreaderservice.service.impl.EventServiceImpl;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/api/v1/event/")
@Slf4j
public class EventController {

    private final EventServiceImpl eventService;

    public EventController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = "")
    public List<EventResponse> getAllEvent() {
        List<Event> result = eventService.getAllEvents();
        log.info("Got request for all events: {}", result);
        return result.stream()
                .map(EventCustomMapper::eventToEventResponse)
                .toList();
    }

    @GetMapping(value = "new")
    public List<EventResponse> getAllEvent(@Min(1) @RequestParam(name = "Limit") long eventsLimit) {
        List<Event> eventList = eventService.getLastEventsByLimit(eventsLimit);
        log.info("Got request for new events: {} with limit: {}", eventList, eventsLimit);
        return eventList.stream()
                .map(EventCustomMapper::eventToEventResponse)
                .toList();
    }

    @GetMapping(value = "own/")
    public List<EventResponse> getAllEventsByCurrentUser() {
        log.info("SecurityContextHolder...getName(): '{}'",
                SecurityContextHolder.getContext().getAuthentication().getName());
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Got request for own events from client '{}'", username);

        List<Event> eventsList = eventService.getAllEventsByUsername(username);
        log.info("Got request for own events: {} by client: {}", eventsList, username);
        return eventsList.stream()
                .map(EventCustomMapper::eventToEventResponse)
                .toList();
    }

    @PostMapping(value = "")
    public EventResponse createEvent(@Valid @RequestBody EventRequest eventRequest) {
        log.info("SecurityContextHolder...getName(): '{}'",
                SecurityContextHolder.getContext().getAuthentication().getName());
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Got request: {} from client '{}'", eventRequest, login);

        Event eventForCreate = EventCustomMapper.eventRequestToEvent(eventRequest);
        Event createdEvent = eventService.createEvent(login, eventForCreate);
        log.info("Created event: {}", createdEvent);

        return EventCustomMapper.eventToEventResponse(createdEvent);
    }

    @GetMapping(value = "{eventId}")
    public EventResponse getEventById(@PathVariable("eventId") @Min(1) Long eventId) {
        log.info("Got eventId: {}", eventId);
        Event foundEvent = eventService.getEventById(eventId);
        log.info("FoundEvent: {}", foundEvent);

        return EventCustomMapper.eventToEventResponse(foundEvent);
    }

    @PutMapping(value = "{eventId}")
    public EventResponse updateEvent(@PathVariable("eventId") @Min(1) Long eventId,
                                     @Valid @RequestBody EventRequest eventRequest) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Got eventId: {} & eventRequest: {} from client '{}'", eventId, eventRequest, userName);
        Event updatedEvent = eventService.updateEvent(
                userName,
                eventId,
                EventCustomMapper.eventRequestToEvent(eventRequest)
        );
        log.info("UpdatedEvent: {}", updatedEvent);
        return EventCustomMapper.eventToEventResponse(updatedEvent);
    }

    @DeleteMapping(value = "{eventId}")
    public void deleteEventById(@PathVariable("eventId") @Min(1) Long eventId) {
        log.info("Got eventId: {} for deleting", eventId);
        eventService.deleteEventById(eventId);
    }
}
