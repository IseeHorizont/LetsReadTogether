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

    @Query(
        value = "SELECT category_name FROM events WHERE active = true " +
                "GROUP BY category_name ORDER BY COUNT(category_name) DESC LIMIT ?1",
        nativeQuery = true
    )
    List<String> getMostPopularEventTagsByLimit(long limit);

    @Query(
        value = "SELECT * FROM events WHERE category_name = ?1",
        nativeQuery = true
    )
    List<Event> findAllByCategoryName(String categoryName);
}
