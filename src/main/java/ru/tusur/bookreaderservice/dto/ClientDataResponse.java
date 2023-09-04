package ru.tusur.bookreaderservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDataResponse {

    private String username; // email

    private String nickname;

    private String avatarImageUrl;

}
