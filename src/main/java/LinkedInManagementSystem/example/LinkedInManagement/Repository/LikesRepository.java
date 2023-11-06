package LinkedInManagementSystem.example.LinkedInManagement.Repository;

import LinkedInManagementSystem.example.LinkedInManagement.Models.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Like, Integer> {
}
