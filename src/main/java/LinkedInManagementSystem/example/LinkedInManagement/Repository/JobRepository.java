package LinkedInManagementSystem.example.LinkedInManagement.Repository;

import LinkedInManagementSystem.example.LinkedInManagement.Models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {

}
