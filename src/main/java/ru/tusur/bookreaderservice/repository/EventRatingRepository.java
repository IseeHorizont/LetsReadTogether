package ru.tusur.bookreaderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tusur.bookreaderservice.entity.Vote;
import ru.tusur.bookreaderservice.entity.VoteKey;

import java.util.List;

@Repository
public interface EventRatingRepository extends JpaRepository<Vote, VoteKey> {

    @Query(
      value = "SELECT * FROM event_rating WHERE event_id = ?1",
      nativeQuery = true
    )
    List<Vote> getAllByEventId(long eventId);
}
