package ru.tusur.bookreaderservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tusur.bookreaderservice.dto.RegisterRequest;
import ru.tusur.bookreaderservice.config.JwtService;
import ru.tusur.bookreaderservice.dto.AuthenticationRequest;
import ru.tusur.bookreaderservice.dto.AuthenticationResponse;
import ru.tusur.bookreaderservice.entity.Role;
import ru.tusur.bookreaderservice.entity.User;
import ru.tusur.bookreaderservice.exception.RegistrationException;
import ru.tusur.bookreaderservice.repository.UserRepository;
import ru.tusur.bookreaderservice.util.ImageGeneratorUtil;

import java.util.Optional;

@Service
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                 JwtService jwtService, AuthenticationManager authManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authManager = authManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        log.info("Got registerRequest: {}", request);

        Optional<User> foundUser = userRepository.findByEmail(request.getEmail());
        if (foundUser.isPresent()) {
            throw new RegistrationException(String.format("Пользователь с email: %s уже зарегистрирован",
                    foundUser.get().getUsername())
            );
        }
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .nickname(request.getNickname())
                .avatarImageUrl(ImageGeneratorUtil.getRandomAvatarUrl())
                .build();

        userRepository.save(user);
        log.info("Saved user: {}", user);

        var jwtToken = jwtService.generateToken(user);
        log.info("Generated user's token: {}", jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.info("Got authenticationRequest: {}", request);
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Пользователь с email: %s не найден", request.getEmail())));
        log.info("Found user: {}", user);

        var jwtToken = jwtService.generateToken(user);
        log.info("Generated user's token: {}", jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(user.getRole().name())
                .build();
    }
}
