package ru.tusur.bookreaderservice.controller;

import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.tusur.bookreaderservice.service.impl.TagServiceImpl;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping(value = "/api/v1/tags")
public class TagsController {

    private final TagServiceImpl tagService;

    public TagsController(TagServiceImpl tagService) {
        this.tagService = tagService;
    }

    @GetMapping(value = "/popular")
    public ResponseEntity<List<String>> getMostPopularEventTags(@Min(1) @RequestParam(name = "limit") long tagsLimit) {
        List<String> tagsList = tagService.getMostPopularEventTagsByLimit(tagsLimit);
        log.info("Got request for new events: {} with limit: {}", tagsList, tagsLimit);
        return ResponseEntity.ok(tagsList);
    }
}
