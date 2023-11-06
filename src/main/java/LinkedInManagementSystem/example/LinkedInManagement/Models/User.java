package LinkedInManagementSystem.example.LinkedInManagement.Models;

import LinkedInManagementSystem.example.LinkedInManagement.EmbdedeClasses.Education;
import LinkedInManagementSystem.example.LinkedInManagement.EmbdedeClasses.Experience;
import LinkedInManagementSystem.example.LinkedInManagement.Enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String userName;

    private String designation;

    private String description;

    private String skills;

    private String email;

    @Embedded
    private Education education;

    @Embedded
    private Experience experience;

    private String achievements;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    private String hiringRoles;

    private String openToWorkRoles;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    List<Job> jobList = new ArrayList<>();

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL)
    @JsonIgnore
    List<Messages> sendMessages = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Like> likeList = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Follow> followerList = new ArrayList<>();


}
