package LinkedInManagementSystem.example.LinkedInManagement.SeriveLayer;

import LinkedInManagementSystem.example.LinkedInManagement.Exceptions.UserIdIsInvalid;
import LinkedInManagementSystem.example.LinkedInManagement.Models.Follow;
import LinkedInManagementSystem.example.LinkedInManagement.Models.User;
import LinkedInManagementSystem.example.LinkedInManagement.Repository.FollowRepository;
import LinkedInManagementSystem.example.LinkedInManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private UserRepository userRepository;

    public void follow(Integer userId, Integer followerId) throws Exception {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (!optionalUser.isPresent()) {
            throw new UserIdIsInvalid("userId is not valid");
        }

        User user = optionalUser.get();

        Optional<User> userOptional = userRepository.findById(followerId);

        if (!userOptional.isPresent()) {
            throw new UserIdIsInvalid("userId is not valid");
        }

        User follower = userOptional.get();
        Follow follow = new Follow();
        follow.setFollowingPersonId(followerId);
        follow.setUser(user);

        user.getFollowerList().add(follow);

        // Save the User entities first
        userRepository.save(user);

        // Then, save the Follow entity
        follow = followRepository.save(follow);
    }

}
