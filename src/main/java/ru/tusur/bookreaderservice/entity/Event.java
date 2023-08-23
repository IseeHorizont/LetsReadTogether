package ru.tusur.bookreaderservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;


@Table(name = "events")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "book_author")
    private String bookAuthor;

    @Column(name = "book_publication_year")
    private String bookPublicationYear;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "active")
    private boolean isActive;
}
