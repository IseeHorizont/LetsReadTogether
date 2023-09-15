package ru.tusur.bookreaderservice.mapper;

import ru.tusur.bookreaderservice.dto.CommentRequest;
import ru.tusur.bookreaderservice.dto.CommentResponse;
import ru.tusur.bookreaderservice.entity.Comment;

public class CommentCustomMapper {

    public static Comment toComment(CommentRequest commentRequest) {
        return Comment.builder()
                .eventId(commentRequest.getEventId())
                .authorName(commentRequest.getUsername()) // here will be an email
                .text(commentRequest.getText())
                .build();
    }

    public static CommentResponse toCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .eventId(comment.getEventId())
                .nickname(comment.getAuthorName())      // here must be user's name, not email
                .text(comment.getText())
                .build();
    }
}
