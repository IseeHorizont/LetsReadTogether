package ru.tusur.bookreaderservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tusur.bookreaderservice.entity.Event;
import ru.tusur.bookreaderservice.entity.User;
import ru.tusur.bookreaderservice.exception.EventServiceException;
import ru.tusur.bookreaderservice.repository.EventRepository;
import ru.tusur.bookreaderservice.repository.UserRepository;
import ru.tusur.bookreaderservice.service.EventService;
import ru.tusur.bookreaderservice.util.ImageGeneratorUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<Event> getAllEvents() {
        List<Event> resultList = eventRepository.findAll().stream()
                .filter(Event::isActive)
                .toList();
        log.info("From Repo we got: {}", resultList);
        return resultList;
    }

    @Transactional
    public List<Event> getLastEventsByLimit(long eventsLimit) {
        List<Event> resultList = eventRepository.findLastEventsByLimit(eventsLimit);
        log.info("From Repo we got: {}", resultList);
        return resultList;
    }

    @Transactional
    public Event createEvent(String userEmail, Event event) {
        log.info("Got for create userEmail:'{}', event: {}", userEmail, event);
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EventServiceException("User not found by email like: " + userEmail));
        event.setUser(user);

        event.setActive(true);
        event.setCreatedAt(LocalDate.now());
        event.setEventImage(event.getEventImage() == null ? ImageGeneratorUtil.EVENT_DEFAULT_IMAGE : event.getEventImage());
        Event createdEvent = eventRepository.save(event);
        log.info("CreatedEvent: {}", createdEvent);
        return createdEvent;
    }

    public Event getEventById(Long eventId) {
        Event foundEvent = eventRepository.findById(eventId)
                        .orElseThrow(() -> new EventServiceException("Event by id#" + eventId + " not found"));
        log.info("FoundEvent: {}", foundEvent);
        if (!foundEvent.isActive()) {
            throw new EventServiceException("Event by id#" + eventId + " not found");
        }
        return foundEvent;
    }

    @Transactional
    public Event updateEvent(String userName, Long eventId, Event event) {
        Optional<Event> foundEvent = eventRepository.findById(eventId);
        event.setActive(true);
        if (foundEvent.isPresent()) {
            Event updatingEvent = Event.builder()
                    .id(eventId)
                    .description(event.getDescription())
                    .eventImage(event.getEventImage())
                    .categoryName(event.getCategoryName())
                    .bookTitle(event.getBookTitle())
                    .bookAuthor(event.getBookAuthor())
                    .bookPublicationYear(event.getBookPublicationYear())
                    .startDate(event.getStartDate())
                    .endDate(event.getEndDate())
                    .createdAt(LocalDate.now())
                    .build();
            Event updatedEvent = eventRepository.save(updatingEvent);
            log.info("Updated event: {}", updatedEvent);
            return updatedEvent;
        } else {
            // create event if it doesn't exist by id
            event.setId(eventId);
            Event createdEvent = eventRepository.save(event);
            log.info("CreatedEvent: {}", createdEvent);
            return createdEvent;
        }
    }

    @Transactional
    public void deleteEventById(Long eventId) {
        Event foundEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventServiceException("Event by id#" + eventId + " for deleting not found"));
        log.info("Found event by id#{}: {}", eventId, foundEvent);
        foundEvent.setActive(false);
        Event deletedEvent = eventRepository.save(foundEvent);
        log.info("Deleted event: {}", deletedEvent);
    }
}
