package LinkedInManagementSystem.example.LinkedInManagement.SeriveLayer;

import LinkedInManagementSystem.example.LinkedInManagement.Exceptions.NoUsersFindInDb;
import LinkedInManagementSystem.example.LinkedInManagement.Exceptions.PostNotFounded;
import LinkedInManagementSystem.example.LinkedInManagement.Models.Comment;
import LinkedInManagementSystem.example.LinkedInManagement.Models.Post;
import LinkedInManagementSystem.example.LinkedInManagement.Models.User;
import LinkedInManagementSystem.example.LinkedInManagement.Repository.CommentRepository;
import LinkedInManagementSystem.example.LinkedInManagement.Repository.PostRepository;
import LinkedInManagementSystem.example.LinkedInManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public void commentForPost(Integer userId,Integer postId,String message) throws Exception{

        Optional<Post> optionalPost = postRepository.findById(postId);

        if(!optionalPost.isPresent()){
            throw new PostNotFounded("there is no posts with this postId");
        }

        Optional<User> optionalUser  = userRepository.findById(userId);

        if(!optionalUser.isPresent()){
            throw new NoUsersFindInDb("there is no user with this userId");
        }

        Post post = optionalPost.get();
        User user = optionalUser.get();

        Comment comment = new Comment();

        comment.setPost(post);
        comment.setUser(user);
        comment.setMessage(message);

        post.getComments().add(comment);
        user.getComments().add(comment);

        commentRepository.save(comment);
    }

}
