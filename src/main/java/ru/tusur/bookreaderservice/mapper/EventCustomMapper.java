package ru.tusur.bookreaderservice.mapper;

import lombok.extern.slf4j.Slf4j;
import ru.tusur.bookreaderservice.dto.EventRequest;
import ru.tusur.bookreaderservice.dto.EventResponse;
import ru.tusur.bookreaderservice.entity.Event;

@Slf4j
public class EventCustomMapper {

    public static Event eventRequestToEvent(EventRequest eventRequest) {
        Event event = new Event();
        event.setDescription(eventRequest.getDescription());

        event.setCategoryName(eventRequest.getCategoryName());
        event.setBookTitle(eventRequest.getBookTitle());
        event.setBookAuthor(eventRequest.getBookAuthor());
        event.setBookPublicationYear(eventRequest.getBookPublicationYear());

        event.setStartDate(eventRequest.getStartDate());
        event.setEndDate(eventRequest.getEndDate());
        log.info("Mapped: {}", event);
        return event;
    }

    public static EventResponse eventToEventResponse(Event event) {
        EventResponse eventResponse = new EventResponse();
        eventResponse.setId(event.getId());
        eventResponse.setDescription(event.getDescription());

        eventResponse.setCategoryName(event.getCategoryName());
        eventResponse.setBookTitle(event.getBookTitle());
        eventResponse.setBookAuthor(event.getBookAuthor());
        eventResponse.setBookPublicationYear(event.getBookPublicationYear());

        eventResponse.setStartDate(event.getStartDate());
        eventResponse.setEndDate(event.getEndDate());

        eventResponse.setCreatorName(event.getUser().getEmail()); // todo replace name or leave an email?

        log.info("Mapped: {}", eventResponse);
        return eventResponse;
    }
}
