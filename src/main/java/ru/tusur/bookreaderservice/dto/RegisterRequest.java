package ru.tusur.bookreaderservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @Pattern(regexp = "^(.+)@(\\S+) $", message = "Не верный формат email-адреса")
    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    private String password;

}
