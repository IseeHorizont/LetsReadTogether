package ru.tusur.bookreaderservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tusur.bookreaderservice.entity.User;
import ru.tusur.bookreaderservice.repository.EventRepository;
import ru.tusur.bookreaderservice.repository.UserRepository;
import ru.tusur.bookreaderservice.service.StatisticService;

@Slf4j
@Service
public class StatisticServiceImpl implements StatisticService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public StatisticServiceImpl(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Long getAllRegisteredClientAmount() {
        return userRepository.findAll().stream().count();
    }

    @Transactional
    public Long getAllActiveClientAmount() {
        return userRepository.findAll().stream()
                .filter(User::isEnabled)
                .count();
    }

    @Transactional
    public Long getAllEventAmount() {
        return eventRepository.findAll().stream().count();
    }

    @Transactional
    public Long getDeletedEventAmount() {
        return eventRepository.findAll()
                .stream()
                .filter(event -> !event.isActive())
                .count();
    }
}
