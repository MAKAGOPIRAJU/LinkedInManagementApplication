package LinkedInManagementSystem.example.LinkedInManagement.EmbdedeClasses;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Education {

    private String collegeName;

    private String collegeLocation;

    private String degree;

    private LocalDate startDate;

    private String stream;

    private LocalDate endDate;
}
