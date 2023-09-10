package ru.tusur.bookreaderservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Имя не должно быть пустым")
    private String nickname;

    @Email(message = "Не верный формат email-адреса", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "email-адрес не должен быть пустым")
    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    private String password;

}
