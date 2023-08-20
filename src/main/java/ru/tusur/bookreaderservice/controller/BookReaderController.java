package ru.tusur.bookreaderservice.controller;

import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.tusur.bookreaderservice.dto.EventRequest;
import ru.tusur.bookreaderservice.dto.EventResponse;
import ru.tusur.bookreaderservice.entity.Event;
import ru.tusur.bookreaderservice.mapper.EventCustomMapper;
//import ru.tusur.bookreaderservice.security.JwtAuthentication;
//import ru.tusur.bookreaderservice.service.impl.AuthService;
import ru.tusur.bookreaderservice.service.impl.EventServiceImpl;

import java.security.Principal;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "/api/v1/event/")
@Slf4j
public class BookReaderController {

    private final EventServiceImpl eventService;

    public BookReaderController(EventServiceImpl eventService) {
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

    @PostMapping(value = "")
//    public EventResponse createEvent(Principal principal, @RequestBody EventRequest eventRequest) {
//        log.info("Got request: {} from client '{}'", eventRequest, principal.getName());
    public EventResponse createEvent(@RequestBody EventRequest eventRequest) {
        String login = "input TestLogin";
        log.info("Got request: {} from client '{}'", eventRequest, login);
        if (eventRequest == null) {
            return null;
        }
        Event eventForCreate = EventCustomMapper.eventRequestToEvent(eventRequest);
        // todo call other service for creator_id
        //eventForCreate.setCreatorId(new Random().nextLong(1, 1000000));
        // todo what do I need to do for category_id?
        //eventForCreate.setCategoryId(new Random().nextLong(1, 10));
        //log.info("Event after adding creatorId & categoryId: {}", eventForCreate);

        //Event createdEvent = eventService.createEvent(principal.getName(), eventForCreate);
        Event createdEvent = eventService.createEvent(login, eventForCreate);
        log.info("Created event: {}", createdEvent);

        return EventCustomMapper.eventToEventResponse(createdEvent);
    }

    @GetMapping(value = "{eventId}")
    public EventResponse getEventById(@PathVariable("eventId") Long eventId) {
        if (eventId == null) {
            return null;
        }
        log.info("Got eventId: {}", eventId);
        Event foundEvent = eventService.getEventById(eventId);
        log.info("FoundEvent: {}", foundEvent);

        return EventCustomMapper.eventToEventResponse(foundEvent);
    }

    @PutMapping(value = "{eventId}")
    public EventResponse updateEvent(Principal principal,
                                     @PathVariable("eventId") Long eventId,
                                     @RequestBody EventRequest eventRequest) {
        log.info("Got eventId: {} & eventRequest: {} from client '{}'", eventId, eventRequest, principal.getName());
        Event updatedEvent = eventService.updateEvent(
                principal.getName(),
                eventId,
                EventCustomMapper.eventRequestToEvent(eventRequest)
        );
        log.info("UpdatedEvent: {}", updatedEvent);

        return EventCustomMapper.eventToEventResponse(updatedEvent);
    }
}
