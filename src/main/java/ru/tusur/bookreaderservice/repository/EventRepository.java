package ru.tusur.bookreaderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tusur.bookreaderservice.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
