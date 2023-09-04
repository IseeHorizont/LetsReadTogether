package ru.tusur.bookreaderservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tusur.bookreaderservice.dto.ClientDataResponse;
import ru.tusur.bookreaderservice.entity.User;
import ru.tusur.bookreaderservice.mapper.ClientCustomMapper;
import ru.tusur.bookreaderservice.service.ClientService;

@RestController
@Validated
@RequestMapping(value = "/api/v1/client/")
@Slf4j
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "badge")
    public ClientDataResponse getClientDataByUsername(@RequestParam(name = "username") String email) {
        log.info("Got user's email: {}", email);
        User foundUser = clientService.getClientDataByUsername(email);
        log.info("FoundUser: {}", foundUser);
        return ClientCustomMapper.userToClientDataResponse(foundUser);
    }
}
