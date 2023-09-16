package ru.tusur.bookreaderservice.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tusur.bookreaderservice.dto.CommentResponse;
import ru.tusur.bookreaderservice.dto.CommentRequest;
import ru.tusur.bookreaderservice.entity.Comment;
import ru.tusur.bookreaderservice.mapper.CommentCustomMapper;
import ru.tusur.bookreaderservice.service.CommentService;

import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/comment")
@Slf4j
@Validated
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = "/")
    public CommentResponse addNewComment(@Valid @RequestBody CommentRequest commentRequest) {
        log.info("Got commentRequest: {} ", commentRequest);

        Comment commentForCreate = CommentCustomMapper.toComment(commentRequest);
        Comment createdComment = commentService.addNewComment(commentForCreate);
        log.info("Created new comment: {}", createdComment);

        return CommentCustomMapper.toCommentResponse(createdComment);
    }

    @GetMapping(value = "")
    public List<Comment> getCommentsByEventId(@Min(1) @RequestParam(name = "eventId") Long eventId) {
        log.info("Got request for comments by eventId#{}", eventId);
        List<Comment> commentsByEventId = commentService.getCommentsByEventId(eventId);
        log.info("Found comments by eventId#{}: {}", eventId, commentsByEventId);
        return commentsByEventId;
    }

    @GetMapping(value = "top")
    public List<Comment> getLastCommentsWithLimit(@Min(3) @RequestParam(name = "limit") long limit) {
        List<Comment> commentsByLimit = commentService.getLastCommentsWithLimit(limit);
        log.info("Found comments with limit={}: {}", limit, commentsByLimit);
        return commentsByLimit;
    }
}
