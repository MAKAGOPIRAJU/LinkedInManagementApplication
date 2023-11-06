package LinkedInManagementSystem.example.LinkedInManagement.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "messages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String message;

    private Integer senderId;

    private Integer receiverId;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private User user;
}
