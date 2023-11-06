package LinkedInManagementSystem.example.LinkedInManagement.ResponseObjects;

import LinkedInManagementSystem.example.LinkedInManagement.LinkedInManagementApplication;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class RequestToAddPost {
    private Integer postingPersonId;

    private String content;

    private Integer likes;


    private String comment;

    private Integer repost;

    private LocalDate localDate;

}
