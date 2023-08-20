package ru.tusur.bookreaderservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tusur.bookreaderservice.entity.Client;
import ru.tusur.bookreaderservice.entity.Event;
import ru.tusur.bookreaderservice.repository.EventRepository;
import ru.tusur.bookreaderservice.service.EventService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        List<Event> resultList = eventRepository.findAll();
        log.info("From Repo we got: {}", resultList);
        return resultList;
    }

    @Transactional
    public Event createEvent(String clientLogin, Event event) {
        Client client = new Client();
        client.setId(1L);
        client.setLogin("TestClient-1");
        //Client client = clientService.getByLogin(clientLogin); // todo call to ClientService ?

        event.setClient(client);
        Event createdEvent = eventRepository.save(event);
        log.info("CreatedEvent: {}", createdEvent);
        return createdEvent;
    }

    public Event getEventById(Long eventId) {
        Event foundEvent = eventRepository.getById(eventId);
        log.info("FoundEvent: {}", foundEvent);
        return foundEvent;
    }

    public Event updateEvent(String clientLogin, Long eventId, Event event) {
        Optional<Event> foundEvent = eventRepository.findById(eventId);
        if (foundEvent.isPresent()) {
            Event updatedEvent = new Event();
            updatedEvent.setId(eventId);
            updatedEvent.setDescription(event.getDescription());
            // todo update categoryName
            updatedEvent.setBookTitle(event.getBookTitle());
            updatedEvent.setBookAuthor(event.getBookAuthor());
            updatedEvent.setBookPublicationYear(event.getBookPublicationYear());
            updatedEvent.setStartDate(event.getStartDate());
            updatedEvent.setEndDate(event.getEndDate());
            //updatedEvent.setCreatorId(event.getCreatorId());
            //updatedEvent.setCategoryId(event.getCategoryId());
            log.info("UpdatedEvent: {}", updatedEvent);
            return updatedEvent;
        } else {
            // todo create if not exist by id
            event.setId(eventId);
            Event createdEvent = createEvent(clientLogin, event);
            log.info("CreatedEvent: {}", createdEvent);
            return createdEvent;
        }
    }


}
