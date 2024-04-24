package ru.sonder.task16.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ru.sonder.task16.DTOs.PostDTO;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "task16-posts")
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
    private User user;

    public void setUser(User user) {
        if (this.user != null) {
            this.user.getPosts().remove(this);
        }
        user.getPosts().add(this);
        this.user = user;
    }

    public void removeUser() {
        if (this.user != null) {
            this.user.getPosts().remove(this);
        }
        this.user = null;
    }

    public PostDTO toDto() {
        return new PostDTO(id, text, creationDate, user == null ? null : user.getId());
    }
}
