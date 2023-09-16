package ru.tusur.bookreaderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "comments")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "author_avatar")
    private String avatar;

    @Column(name = "text")
    private String text;

    @Column(name = "created_date")
    private LocalDate createdAt;

}
