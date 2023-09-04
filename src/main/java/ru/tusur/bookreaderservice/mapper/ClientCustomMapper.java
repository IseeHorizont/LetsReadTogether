package ru.tusur.bookreaderservice.mapper;

import lombok.extern.slf4j.Slf4j;
import ru.tusur.bookreaderservice.dto.ClientDataResponse;
import ru.tusur.bookreaderservice.entity.User;

@Slf4j
public class ClientCustomMapper {

    public static ClientDataResponse userToClientDataResponse(User user) {
        return ClientDataResponse.builder()
                .username(user.getUsername())
                .nickname(user.getNickname())
                .avatarImageUrl(user.getAvatarImageUrl())
                .build();
    }
}
