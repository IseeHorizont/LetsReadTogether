package ru.tusur.bookreaderservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tusur.bookreaderservice.entity.User;
import ru.tusur.bookreaderservice.exception.ClientServiceException;
import ru.tusur.bookreaderservice.repository.UserRepository;
import ru.tusur.bookreaderservice.service.ClientService;

import java.util.Optional;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final UserRepository userRepository;

    public ClientServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User getClientDataByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new ClientServiceException("User not found by email like: " + username));
    }

    @Override
    public Long getClientIdByEmail(String email) {
        Optional<User> foundUser = userRepository.findByEmail(email);
        if (foundUser.isEmpty()) {
            new ClientServiceException("User not found by email: " + email);
        }
        return foundUser.get().getId();
    }
}
