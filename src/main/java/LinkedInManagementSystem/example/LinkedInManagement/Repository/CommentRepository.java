package LinkedInManagementSystem.example.LinkedInManagement.Repository;

import LinkedInManagementSystem.example.LinkedInManagement.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment , Integer> {
}
