package ru.tusur.bookreaderservice.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "event_rating")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vote {

    @EmbeddedId
    private VoteKey voteKey;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private VoteType vote;
}
