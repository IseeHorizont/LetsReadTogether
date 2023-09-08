package ru.tusur.bookreaderservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class VoteKey implements Serializable {

    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "client_id")
    private Long clientId;
}
