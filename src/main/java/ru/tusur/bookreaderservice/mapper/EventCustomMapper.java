package ru.tusur.bookreaderservice.mapper;

import lombok.extern.slf4j.Slf4j;
import ru.tusur.bookreaderservice.dto.EventRequest;
import ru.tusur.bookreaderservice.dto.EventResponse;
import ru.tusur.bookreaderservice.entity.Event;
import ru.tusur.bookreaderservice.util.ImageGeneratorUtil;

import java.time.format.DateTimeFormatter;

@Slf4j
public class EventCustomMapper {

    public static Event eventRequestToEvent(EventRequest eventRequest) {
        Event event = Event.builder()
                .eventTitle(eventRequest.getEventTitle())
                .description(eventRequest.getDescription())
                .eventImage(eventRequest.getEventImage())
                .categoryName(eventRequest.getCategoryName())
                .bookTitle(eventRequest.getBookTitle())
                .bookAuthor(eventRequest.getBookAuthor())
                .bookPublicationYear(eventRequest.getBookPublicationYear())

                .startDate(eventRequest.getStartDate())
                .endDate(eventRequest.getEndDate())
                .build();

        //log.info("Mapped: {}", event);
        return event;
    }

    public static EventResponse eventToEventResponse(Event event) {
        EventResponse eventResponse = EventResponse.builder()
                .id(event.getId())
                .eventTitle(event.getEventTitle())
                .description(event.getDescription())
                .eventImage(event.getEventImage()== null ? ImageGeneratorUtil.EVENT_DEFAULT_IMAGE
                                                         : event.getEventImage()
                )
                .categoryName(event.getCategoryName())
                .bookTitle(event.getBookTitle())
                .bookAuthor(event.getBookAuthor())
                .bookPublicationYear(event.getBookPublicationYear())

                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .createdAt(event.getCreatedAt() == null
                        ? "24.02.2022"
                        : event.getCreatedAt().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                )

                // todo extract to facade
                .creatorName(event.getUser().getNickname() == null ? "Странный незнакомец" : event.getUser().getNickname())
                .avatar(event.getUser().getAvatarImageUrl() == null ? ImageGeneratorUtil.USER_DEFAULT_AVATAR
                                                                    : event.getUser().getAvatarImageUrl()
                )
                .creatorEmail(event.getUser().getUsername())

                .commentCounter(event.getCommentCounter() == null ? 0 : event.getCommentCounter())
                .build();

        //log.info("Mapped: {}", eventResponse);
        return eventResponse;
    }
}
