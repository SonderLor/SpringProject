package ru.sonder.task22.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ru.sonder.task22.DTOs.PostDTO;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @SequenceGenerator(name = "posts_seq", sequenceName = "posts_sequence", allocationSize = 1)
    @GeneratedValue(generator = "posts_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @JsonIgnore
    @ManyToOne
    private Person person;

    public void setPerson(Person person) {
        if (this.person != null) {
            this.person.getPosts().remove(this);
        }
        person.getPosts().add(this);
        this.person = person;
    }

    public void removeUser() {
        if (this.person != null) {
            this.person.getPosts().remove(this);
        }
        this.person = null;
    }

    public PostDTO toDto() {
        return new PostDTO(id, text, creationDate, person == null ? null : person.getId());
    }
}
