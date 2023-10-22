package ru.tusur.bookreaderservice.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tusur.bookreaderservice.entity.Event;
import ru.tusur.bookreaderservice.entity.User;
import ru.tusur.bookreaderservice.repository.EventRepository;
import ru.tusur.bookreaderservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CommentServiceImpl commentService;

    @InjectMocks
    private EventServiceImpl eventService;


    @Test
    public void getAllEvents_shouldReturnAllEvents() {
        List<Event> eventList = getEventList();
        Mockito.when(eventRepository.findAll()).thenReturn(eventList);

        List<Event> resultEventList = eventService.getAllEvents();
        Assertions.assertNotNull(resultEventList);
        Assertions.assertIterableEquals(eventList, resultEventList);
    }

    @Test
    public void getAllEventsByCategoryName_shouldReturnEventsByCategory() {
        String needCategoryName = "test-category-1";
        List<Event> eventList = getEventListByTheSameCategory();
        Mockito.when(eventRepository.findAllByCategoryName(needCategoryName))
               .thenReturn(eventList);

        List<Event> filteredEventListByCategory
                = eventService.getAllEventsByCategoryName(needCategoryName);
        Assertions.assertNotNull(filteredEventListByCategory);
        Assertions.assertIterableEquals(eventList, filteredEventListByCategory);
    }

    @Test
    public void getEventById_shouldReturnEventById() {
        Event event = getEventList().get(0);
        Mockito.when(eventRepository.findById(event.getId())).thenReturn(Optional.of(event));

        Event foundEvent = eventService.getEventById(event.getId());
        Assertions.assertNotNull(foundEvent);
        Assertions.assertEquals(event.getId(), foundEvent.getId());
    }

    @Test
    public void createEvent_shouldCreateNewEvent() {
        User user = getTestUser();
        Event eventForCreate = getEventList().get(0);
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        Mockito.when(eventRepository.save(eventForCreate)).thenReturn(eventForCreate);

        Event createdEvent = eventService.createEvent(user.getEmail(), eventForCreate);
        Assertions.assertNotNull(createdEvent);
        Assertions.assertEquals(eventForCreate.getId(), createdEvent.getId());
    }

    private User getTestUser() {
        User user = User.builder()
                .id(1L)
                .email("test@test.com")
                .password("test-password")
                .build();
        return user;
    }

    private List<Event> getEventList() {
        Event event1 = Event.builder()
                .id(1L)
                .description("description-1")
                .categoryName("test-category-1")
                .isActive(true)
                .build();
        Event event2 = Event.builder()
                .id(2L)
                .description("description-2")
                .categoryName("test-category-2")
                .isActive(true)
                .build();
        return List.of(event1, event2);
    }

    private List<Event> getEventListByTheSameCategory() {
        Event eventByCategory = Event.builder()
                .id(1L)
                .description("description-1")
                .categoryName("test-category-1")
                .isActive(true)
                .build();
        return List.of(eventByCategory);
    }
}
