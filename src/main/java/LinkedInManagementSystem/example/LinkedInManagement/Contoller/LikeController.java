package LinkedInManagementSystem.example.LinkedInManagement.Contoller;

import LinkedInManagementSystem.example.LinkedInManagement.SeriveLayer.LikeServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
public class LikeController {

    @Autowired
    private LikeServie likeServie;

    @PutMapping("/likePost/{userId}/{postId}")
    public ResponseEntity<Void> likePost(@PathVariable("userId") Integer userId ,
                                         @PathVariable("postId") Integer postId) throws Exception{

       try {
           likeServie.likePost(userId , postId);
           return new ResponseEntity<>(HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }
}
