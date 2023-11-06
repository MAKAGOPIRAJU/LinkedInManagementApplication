package LinkedInManagementSystem.example.LinkedInManagement.Contoller;

import LinkedInManagementSystem.example.LinkedInManagement.SeriveLayer.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @PutMapping("/followSomeone")
    private ResponseEntity follow(@RequestParam ("userId") Integer userId,
                                  @RequestParam ("followerId") Integer followerId) throws Exception{
      try {
          followService.follow(userId , followerId);
         return new ResponseEntity("added",HttpStatus.OK);
      }catch ( Exception e){
         return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
      }
    }
}
