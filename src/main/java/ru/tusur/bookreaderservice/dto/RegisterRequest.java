package ru.tusur.bookreaderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname; // todo maybe instead of login
    private String lastname; // todo
    private String email;
    private String password;

    // todo some fields?

}
