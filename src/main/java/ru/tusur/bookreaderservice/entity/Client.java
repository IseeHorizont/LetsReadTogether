package ru.tusur.bookreaderservice.entity;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "clients")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    //private String firstName;
    //private String lastName;
    //private Set<Role> roles;

}
