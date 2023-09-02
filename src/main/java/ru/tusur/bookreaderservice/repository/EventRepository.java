package ru.tusur.bookreaderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tusur.bookreaderservice.entity.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(
        value = "SELECT * FROM events WHERE created_date IS NOT NULL AND active = true " +
                "ORDER BY created_date DESC " +
                "LIMIT ?1",
        nativeQuery = true
    )
    List<Event> findLastEventsByLimit(long limit);

    @Query(
        value = "SELECT * FROM events WHERE user_id = ?1",
        nativeQuery = true
    )
    List<Event> findAllByUsername(Long userId);
}
