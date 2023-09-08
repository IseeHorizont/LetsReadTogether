package ru.tusur.bookreaderservice.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tusur.bookreaderservice.dto.EventRating;
import ru.tusur.bookreaderservice.dto.EventRatingResponse;
import ru.tusur.bookreaderservice.mapper.EventRatingCustomMapper;
import ru.tusur.bookreaderservice.service.EventRatingService;

@Slf4j
@RestController
@Validated
@RequestMapping(value = "/api/v1/rating")
public class RatingController {

    private EventRatingService eventRatingService;

    public RatingController(EventRatingService eventRatingService) {
        this.eventRatingService = eventRatingService;
    }

    @GetMapping(value = "{eventId}")
    public EventRatingResponse getEventRatingById(@PathVariable("eventId") @Min(1) Long eventId) {
        log.info("Got eventId: {} for getting rating", eventId);
        EventRating foundEventRating = eventRatingService.getEventRatingById(eventId);
        log.info("Found rating by eventId#{}: {}", eventId, foundEventRating);
        return EventRatingCustomMapper.eventRatingToEventRatingResponse(foundEventRating);
    }
}
