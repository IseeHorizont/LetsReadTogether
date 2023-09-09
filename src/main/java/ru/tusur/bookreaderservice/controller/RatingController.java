package ru.tusur.bookreaderservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tusur.bookreaderservice.dto.EventRating;
import ru.tusur.bookreaderservice.dto.EventRatingResponse;
import ru.tusur.bookreaderservice.dto.VoteRequest;
import ru.tusur.bookreaderservice.entity.Vote;
import ru.tusur.bookreaderservice.entity.VoteKey;
import ru.tusur.bookreaderservice.entity.VoteType;
import ru.tusur.bookreaderservice.mapper.EventRatingCustomMapper;
import ru.tusur.bookreaderservice.service.EventRatingService;

import java.util.List;

@Slf4j
@Validated
@RestController
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

    @GetMapping(value = "/")
    public List<EventRating> getAllEventRating() {
        List<EventRating> allRatings = eventRatingService.getAllEventRating();
        return allRatings;
    }

    @PostMapping("/")
    public Vote addVote(@Valid @RequestBody VoteRequest voteRequest) {
        log.info("Got VoteRequest: {}", voteRequest);
        Vote newVote = Vote.builder()
                .voteKey(new VoteKey(voteRequest.getEventId(), voteRequest.getClientId()))
                .vote(voteRequest.getVote().equals("LIKE") ? VoteType.LIKE : VoteType.DISLIKE)
                .build();
        Vote createdVote = eventRatingService.addVote(newVote);
        log.info("Created new Vote: {}", createdVote);
        return createdVote;
    }
}
