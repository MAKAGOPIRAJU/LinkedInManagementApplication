package LinkedInManagementSystem.example.LinkedInManagement.Models;

import LinkedInManagementSystem.example.LinkedInManagement.Enums.ExperienceLevel;
import LinkedInManagementSystem.example.LinkedInManagement.Enums.JobStatus;
import LinkedInManagementSystem.example.LinkedInManagement.Enums.JobType;
import LinkedInManagementSystem.example.LinkedInManagement.Enums.WorkLocation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String jobTitle;

    private Integer recruiterId;

    private String jobDescription;

    private String companyName;

    @ElementCollection
    private String[] skillSet;

    private Integer noOfVacancies;

    @Enumerated(value = EnumType.STRING)
    private WorkLocation workLocation;

    private double salary;

    @Enumerated(value = EnumType.STRING)
    private JobStatus jobStatus;


    private LocalDate postingDate;

    private LocalDate closingDate;


    @Enumerated(value = EnumType.STRING)
    private JobType jobType;

    @Enumerated(value = EnumType.STRING)
    private ExperienceLevel experienceLevel;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private User user;

}
