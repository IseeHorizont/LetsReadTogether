package ru.tusur.bookreaderservice.service;

import ru.tusur.bookreaderservice.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment addNewComment(Comment comment);

    List<Comment> getCommentsByEventId(Long eventId);

    Long getCommentCounterByEventId(Long eventId);
}
