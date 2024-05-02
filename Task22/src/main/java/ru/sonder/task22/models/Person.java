package ru.sonder.task22.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sonder.task22.DTOs.PersonDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "persons")
public class Person {
    @Id
    @SequenceGenerator(name = "persons_seq", sequenceName = "persons_sequence", allocationSize = 1)
    @GeneratedValue(generator = "persons_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    public PersonDTO toDto() {
        return new PersonDTO(id, firstName, lastName, middleName, birthDate, posts.stream().map(Post::toDto).toList());
    }
}
