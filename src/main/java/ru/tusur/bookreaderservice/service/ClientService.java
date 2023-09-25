package ru.tusur.bookreaderservice.service;


import ru.tusur.bookreaderservice.entity.User;

public interface ClientService {

    User getClientDataByUsername(String username);          // for this username = email

    Long getClientIdByEmail(String email);
}
