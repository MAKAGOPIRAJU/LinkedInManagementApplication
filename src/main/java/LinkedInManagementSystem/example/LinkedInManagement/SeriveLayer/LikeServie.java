package LinkedInManagementSystem.example.LinkedInManagement.SeriveLayer;

import LinkedInManagementSystem.example.LinkedInManagement.Exceptions.NoUsersFindInDb;
import LinkedInManagementSystem.example.LinkedInManagement.Exceptions.PostNotFounded;
import LinkedInManagementSystem.example.LinkedInManagement.Models.Like;
import LinkedInManagementSystem.example.LinkedInManagement.Models.Post;
import LinkedInManagementSystem.example.LinkedInManagement.Models.User;
import LinkedInManagementSystem.example.LinkedInManagement.Repository.LikesRepository;
import LinkedInManagementSystem.example.LinkedInManagement.Repository.PostRepository;
import LinkedInManagementSystem.example.LinkedInManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServie {
    @Autowired
    private LikesRepository likesRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public void likePost(Integer userId , Integer postId) throws Exception

    {

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

        Like like = new Like();

        like.setPost(post);
        like.setUser(user);

         post.getLikeList().add(like);
         user.getLikeList().add(like);

         likesRepository.save(like);
    }
}
