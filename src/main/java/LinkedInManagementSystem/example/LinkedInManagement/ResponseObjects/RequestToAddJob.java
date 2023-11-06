package LinkedInManagementSystem.example.LinkedInManagement.ResponseObjects;

import LinkedInManagementSystem.example.LinkedInManagement.Enums.JobStatus;
import LinkedInManagementSystem.example.LinkedInManagement.Enums.WorkLocation;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RequestToAddJob {

    private String jobTitle;

    private String jobDescription;

    private String companyName;

    private String skillSet;

    private Integer recruiterId; // here it will took by both the company and recruiter

    private Integer noOfVacancies;

    @Enumerated(value = EnumType.STRING)
    private WorkLocation workLocation;

    private double salary;
    @Enumerated(value = EnumType.STRING)
    private JobStatus jobStatus;

    private LocalDate postingDate;

    private LocalDate closingDate;
}
