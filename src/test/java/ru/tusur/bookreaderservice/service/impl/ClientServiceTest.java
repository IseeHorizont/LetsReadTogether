package ru.tusur.bookreaderservice.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tusur.bookreaderservice.entity.User;
import ru.tusur.bookreaderservice.repository.UserRepository;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    public void getClientDataByUsername_shouldReturnUserData() {
        User user = getTestUser();
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        User foundUser = clientService.getClientDataByUsername(user.getUsername());
        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals(user.getUsername(), foundUser.getUsername());
        verify(userRepository).findByEmail(user.getEmail());
    }

    @Test
    public void getClientIdByEmail_shouldReturnUserIdByEmail() {
        User user = getTestUser();
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        Long foundUserId = clientService.getClientIdByEmail(user.getEmail());
        Assertions.assertNotNull(foundUserId);
        Assertions.assertEquals(user.getId(), foundUserId);
        verify(userRepository).findByEmail(user.getEmail());
    }

    private User getTestUser() {
        User user = User.builder()
                .id(1L)
                .email("test@test.com")
                .password("test-password")
                .build();
        return user;
    }
}
