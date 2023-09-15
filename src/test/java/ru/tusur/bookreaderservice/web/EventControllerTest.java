package ru.tusur.bookreaderservice.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tusur.bookreaderservice.controller.EventController;
import ru.tusur.bookreaderservice.dto.EventResponse;
import ru.tusur.bookreaderservice.entity.Event;
import ru.tusur.bookreaderservice.mapper.EventCustomMapper;
import ru.tusur.bookreaderservice.service.impl.EventServiceImpl;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventControllerTest {

    @Mock
    private EventServiceImpl eventService;

    @InjectMocks
    private EventController eventController;


    @Test
    void getAllEvent() {

    }

    @Test
    void getAllEventByCategoryName() {
        // String categoryName
    }

    @Test
    void createEvent() {

    }

    @Test
    void getEventById() {
        final Event event = mock(Event.class);
        when(eventService.getEventById(1L)).thenReturn(event);

        Event eventFromController = eventController.getEventById(1L);
        Assertions.assertNotNull(eventFromController);
        Assertions.assertEquals(eventResponse, eventFromController);
        verify(eventService).getEventById(1L);
    }
}
