package LinkedInManagementSystem.example.LinkedInManagement.SeriveLayer;

import LinkedInManagementSystem.example.LinkedInManagement.Enums.JobType;
import LinkedInManagementSystem.example.LinkedInManagement.Exceptions.DidNotHaveAnyFollowers;
import LinkedInManagementSystem.example.LinkedInManagement.Exceptions.NoJobsArePostedYet;
import LinkedInManagementSystem.example.LinkedInManagement.Exceptions.NoPostsAddedByUser;
import LinkedInManagementSystem.example.LinkedInManagement.Exceptions.NoUsersFindInDb;
import LinkedInManagementSystem.example.LinkedInManagement.Models.Follow;
import LinkedInManagementSystem.example.LinkedInManagement.Models.Job;
import LinkedInManagementSystem.example.LinkedInManagement.Models.Post;
import LinkedInManagementSystem.example.LinkedInManagement.Models.User;
import LinkedInManagementSystem.example.LinkedInManagement.Repository.JobRepository;
import LinkedInManagementSystem.example.LinkedInManagement.Repository.PostRepository;
import LinkedInManagementSystem.example.LinkedInManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

  @Autowired
  private JavaMailSender mailSender;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private JobRepository jobRepository;

    public String registerUser(User user){

        userRepository.save(user);

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        String body = "Hi "+user.getUserName()+" !" +
                "You have successfully registered. You can start expose your self through the world.";

        mailMessage.setFrom("makasrinivasulu01@gmail.com"); // from which mail u want to send
        mailMessage.setTo(user.getEmail());//to which one send to mail
        mailMessage.setSubject("Welcome To LinkedInManagement System !!");//subject
        mailMessage.setText(body);//message in the box

        mailSender.send(mailMessage);

        return "user added successfully to the db";
    }
    public List<User> allUsers() throws Exception{

        List<User> userList = userRepository.findAll();

        if(userList.isEmpty()) {
            throw new NoUsersFindInDb("users not exist in db");
        }

        return userList;
    }

    public List<Post> allPosts(Integer userId) throws Exception{

        Optional<User> optionalUser = userRepository.findById(userId);

        if(!optionalUser.isPresent()) {
            throw new NoUsersFindInDb("enter a valid a user id");
        }

        List<Post> postList = optionalUser.get().getPostList();

        if(postList.isEmpty()){
            throw new NoPostsAddedByUser("no posts added by user");
        }

        return postList;
    }
    public List<Job> allInternShips(JobType jobType) throws Exception{

        List<Job> jobList = jobRepository.findAll();

        if(jobList.isEmpty()){
            throw new NoJobsArePostedYet("no internships are posted yet");
        }

        List<Job> ans = new ArrayList<>();

        for(Job job: jobList) {

            if(job.getJobType().equals(jobType)) ans.add(job);
        }

        if(ans.isEmpty()) {
            throw new NoJobsArePostedYet("no internships are posted yet");
        }

        return ans;
    }

    public List<User> allUsers(Integer userId) throws Exception{

        Optional<User> optionalUser = userRepository.findById(userId);

        if(!optionalUser.isPresent()) {
            throw new NoUsersFindInDb("enter a valid a user id");
        }

        User user = optionalUser.get();

        List<Follow> followList = user.getFollowerList();

        List<User> ansList = new ArrayList<>();

        for(Follow follow : followList){

            ansList.add((userRepository.findById(follow.getFollowingPersonId())).get());
        }

        if(ansList.isEmpty()){
            throw new DidNotHaveAnyFollowers("did not have any followers yet");
        }

        return ansList;
    }

}
