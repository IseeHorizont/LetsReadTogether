package ru.tusur.bookreaderservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tusur.bookreaderservice.repository.EventRepository;
import ru.tusur.bookreaderservice.service.TagService;

import java.util.List;

@Service
@Slf4j
public class TagServiceImpl implements TagService {

    private final EventRepository eventRepository;

    public TagServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Transactional
    public List<String> getMostPopularEventTagsByLimit(long tagsLimit) {
        List<String> resultList = eventRepository.getMostPopularEventTagsByLimit(tagsLimit);
        log.info("Most popular events' tags: {}", resultList);
        return resultList;
    }
}
