package ru.tusur.bookreaderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tusur.bookreaderservice.entity.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(
        value = "SELECT * FROM comments WHERE event_id = ?1",
        nativeQuery = true
    )
    List<Comment> findAllByEventId(Long eventId);

    @Query(
        value = "SELECT COUNT(*) FROM comments WHERE event_id = ?1",
        nativeQuery = true
    )
    Long getCommentCounterByEventId(Long eventId);
}
