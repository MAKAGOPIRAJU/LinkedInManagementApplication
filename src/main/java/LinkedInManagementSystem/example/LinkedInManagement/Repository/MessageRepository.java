package LinkedInManagementSystem.example.LinkedInManagement.Repository;

import LinkedInManagementSystem.example.LinkedInManagement.Models.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Messages , Integer> {
}
