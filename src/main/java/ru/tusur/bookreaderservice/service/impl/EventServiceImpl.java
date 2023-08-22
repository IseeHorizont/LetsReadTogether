package ru.tusur.bookreaderservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.tusur.bookreaderservice.entity.Event;
import ru.tusur.bookreaderservice.entity.User;
import ru.tusur.bookreaderservice.exception.EventServiceException;
import ru.tusur.bookreaderservice.repository.EventRepository;
import ru.tusur.bookreaderservice.repository.UserRepository;
import ru.tusur.bookreaderservice.service.EventService;

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

    public List<Event> getAllEvents() {
        List<Event> resultList = eventRepository.findAll();
        log.info("From Repo we got: {}", resultList);
        return resultList;
    }

    @Transactional
    public Event createEvent(String userEmail, Event event) {
        log.info("Got for create userEmail:'{}', event: {}", userEmail, event);
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EventServiceException("User not found by email like: " + userEmail));
        event.setUser(user);

        Event createdEvent = eventRepository.save(event);
        log.info("CreatedEvent: {}", createdEvent);
        return createdEvent;
    }

    public Event getEventById(Long eventId) {
        Event foundEvent = eventRepository.findById(eventId)
                        .orElseThrow(() -> new EventServiceException("User by id#" + eventId + " not found"));
        log.info("FoundEvent: {}", foundEvent);
        return foundEvent;
    }

    @Transactional
    public Event updateEvent(String userName, Long eventId, Event event) {
        Optional<Event> foundEvent = eventRepository.findById(eventId);
        if (foundEvent.isPresent()) {
            Event updatedEvent = new Event();
            updatedEvent.setId(eventId);
            updatedEvent.setDescription(event.getDescription());
            updatedEvent.setCategoryName(event.getCategoryName());
            updatedEvent.setBookTitle(event.getBookTitle());
            updatedEvent.setBookAuthor(event.getBookAuthor());
            updatedEvent.setBookPublicationYear(event.getBookPublicationYear());
            updatedEvent.setStartDate(event.getStartDate());
            updatedEvent.setEndDate(event.getEndDate());
            log.info("UpdatedEvent: {}", updatedEvent);
            return updatedEvent;
        } else {
            // create event if it doesn't exist by id
            event.setId(eventId);
            Event createdEvent = createEvent(userName, event);
            log.info("CreatedEvent: {}", createdEvent);
            return createdEvent;
        }
    }

    @Transactional
    public void deleteEventById(Long eventId) {
        // todo change status in-active instead of deleting
    }
}
