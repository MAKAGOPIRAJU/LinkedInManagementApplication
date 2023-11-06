package LinkedInManagementSystem.example.LinkedInManagement.Contoller;

import LinkedInManagementSystem.example.LinkedInManagement.Enums.JobType;
import LinkedInManagementSystem.example.LinkedInManagement.Models.Job;
import LinkedInManagementSystem.example.LinkedInManagement.Models.Post;
import LinkedInManagementSystem.example.LinkedInManagement.Models.User;
import LinkedInManagementSystem.example.LinkedInManagement.SeriveLayer.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/addUser")
    public ResponseEntity registerUser(@RequestBody User user) {

        String res = userService.registerUser(user);

        return new ResponseEntity<>(res , HttpStatus.OK);
    }

    @GetMapping("/allUsers")
    public ResponseEntity allUsers() throws Exception{

        try{
            List<User> userList = userService.allUsers();
            return new ResponseEntity(userList , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // all posts posted by user
    @GetMapping("/allPosts")
    public ResponseEntity allPost(Integer userId) throws Exception{

        try{
            List<Post> postList = userService.allPosts(userId);
            return new ResponseEntity(postList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    // find all internships jobs
    @GetMapping("/internships")
    public ResponseEntity allInternShips(@RequestParam("jobType") JobType jobType) throws Exception{

        try{
          List<Job> jobList = userService.allInternShips(jobType);
          return new ResponseEntity(jobList , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    // find all the followers of user
    @GetMapping("/allFollowers/{userId}")

    public ResponseEntity allFollowers(@PathVariable ("userId") Integer userId){

        try {
            List<User> userList = userService.allUsers(userId);
            return new ResponseEntity(userList,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

}
