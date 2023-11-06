package LinkedInManagementSystem.example.LinkedInManagement.EmbdedeClasses;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Embeddable
@Getter
@Setter
public class Experience {

    private String role;

    private String company;

    private LocalDate start;

    private LocalDate end;
}
