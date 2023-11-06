package LinkedInManagementSystem.example.LinkedInManagement.Models;


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
@Table(name = "company")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyId;

    private String name;

    private Integer followers;

    private Integer following;

    private Integer totalEmployees;

    private Integer frontendEngineers;

    private Integer backendEngineers;

    private Integer softwareEngineers;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    private String hiringRoles;



    @OneToMany(mappedBy = "company" ,cascade = CascadeType.ALL)
    @JsonIgnore
    List<Post> postList = new ArrayList<>();
}
