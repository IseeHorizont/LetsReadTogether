package ru.tusur.bookreaderservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class VoteKey implements Serializable {

    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "client_id")
    private Long clientId;
}
