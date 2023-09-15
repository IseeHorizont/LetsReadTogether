package ru.tusur.bookreaderservice.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tusur.bookreaderservice.dto.EventRating;
import ru.tusur.bookreaderservice.entity.Vote;
import ru.tusur.bookreaderservice.entity.VoteKey;
import ru.tusur.bookreaderservice.entity.VoteType;
import ru.tusur.bookreaderservice.repository.EventRatingRepository;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EventRatingServiceTest {

    @Mock
    private EventRatingRepository eventRatingRepository;

    @InjectMocks
    private EventRatingServiceImpl eventRatingService;


    @Test
    public void getEventRatingById_shouldReturnEventRatingById() {
        long eventId = 1L;
        Vote vote1 = Vote.builder()
                .voteKey(new VoteKey(eventId, 5L))
                .vote(VoteType.LIKE)
                .build();
        Vote vote2 = Vote.builder()
                .voteKey(new VoteKey(eventId, 12L))
                .vote(VoteType.DISLIKE)
                .build();

        List<Vote> allVotes = List.of(vote1, vote2);
        Mockito.when(eventRatingRepository.getAllByEventId(eventId)).thenReturn(allVotes);

        EventRating resultFromService = eventRatingService.getEventRatingById(eventId);
        Assertions.assertNotNull(resultFromService);
        Assertions.assertEquals(1, resultFromService.getLikeCounter());
        Assertions.assertEquals(1, resultFromService.getDislikeCounter());
    }

    @Test
    public void addVote_shouldReturnNewVote() {
        Vote newVote = getVotesList().get(0);
        Mockito.when(eventRatingRepository.save(newVote)).thenReturn(newVote);

        Vote createdVote = eventRatingService.addVote(newVote);
        Assertions.assertNotNull(createdVote);
        Assertions.assertEquals(newVote, createdVote);
    }

    private List<Vote> getVotesList() {
        Vote vote1 = Vote.builder()
                .voteKey(new VoteKey(1L, 1L))
                .vote(VoteType.LIKE)
                .build();
        Vote vote2 = Vote.builder()
                .voteKey(new VoteKey(2L, 1L))
                .vote(VoteType.DISLIKE)
                .build();
        return List.of(vote1, vote2);
    }
}
