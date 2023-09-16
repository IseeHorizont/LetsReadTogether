package ru.tusur.bookreaderservice.mapper;

import ru.tusur.bookreaderservice.dto.CommentRequest;
import ru.tusur.bookreaderservice.dto.CommentResponse;
import ru.tusur.bookreaderservice.entity.Comment;

public class CommentCustomMapper {

    public static Comment toComment(CommentRequest commentRequest) {
        return Comment.builder()
                .eventId(commentRequest.getEventId())
                .authorName(commentRequest.getUsername())
                .text(commentRequest.getText())
                .build();
    }

    public static CommentResponse toCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .eventId(comment.getEventId())
                .nickname(comment.getAuthorName())
                .avatar(comment.getAvatar())
                .text(comment.getText())
                .build();
    }
}
