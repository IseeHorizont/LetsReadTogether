package ru.tusur.bookreaderservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tusur.bookreaderservice.dto.EventRating;
import ru.tusur.bookreaderservice.entity.Vote;
import ru.tusur.bookreaderservice.entity.VoteType;
import ru.tusur.bookreaderservice.repository.EventRatingRepository;
import ru.tusur.bookreaderservice.service.EventRatingService;

import java.util.List;

@Service
@Slf4j
public class EventRatingServiceImpl implements EventRatingService {

    private EventRatingRepository eventRatingRepository;

    public EventRatingServiceImpl(EventRatingRepository eventRatingRepository) {
        this.eventRatingRepository = eventRatingRepository;
    }

    @Transactional
    public EventRating getEventRatingById(long eventId) {
        List<Vote> voteList = eventRatingRepository.getAllByEventId(eventId);
        log.info("Got from repo list of votes: {}", voteList);
        return EventRating.builder()
                .eventId(eventId)
                .likeCounter(
                    voteList.stream()
                        .filter(vote -> vote.getVote().equals(VoteType.LIKE))
                        .count()
                )
                .dislikeCounter(
                    voteList.stream()
                        .filter(vote -> vote.getVote().equals(VoteType.DISLIKE))
                        .count()
                )
                .build();
    }

    @Transactional
    public Vote addVote(Vote newVote) {
        Vote createdVote = eventRatingRepository.save(newVote);
        log.info("Created new vote: {}", createdVote);
        return createdVote;
    }

}
