package LinkedInManagementSystem.example.LinkedInManagement.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    private String content;


    private String comment;

    private Integer repost;

    private LocalDate localDate;

    private String name;

    // user and companies both are able to post

    // one user can do multiple posts
    // one company can do multiple posts

    // post to user    ---> many to one
    // post to company ---> many to one

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Company company;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Like> likeList = new ArrayList<>();

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();
}
