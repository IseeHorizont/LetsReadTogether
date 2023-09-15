package ru.tusur.bookreaderservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentResponse {

    private Long eventId;

    private String nickname;

    private String text;
}
