package LinkedInManagementSystem.example.LinkedInManagement.Contoller;

import LinkedInManagementSystem.example.LinkedInManagement.SeriveLayer.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PutMapping("/commentForPost")
    public ResponseEntity<Void> commentForPost(@RequestParam ("userId")Integer userId,
                                               @RequestParam ("postId")Integer postId,
                                               @RequestParam("message") String message) throws Exception{
        try {
            commentService.commentForPost(userId,postId , message);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
