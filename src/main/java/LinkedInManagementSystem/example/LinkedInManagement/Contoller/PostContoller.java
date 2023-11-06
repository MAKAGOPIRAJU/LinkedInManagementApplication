package LinkedInManagementSystem.example.LinkedInManagement.Contoller;

import LinkedInManagementSystem.example.LinkedInManagement.Models.Post;
import LinkedInManagementSystem.example.LinkedInManagement.Models.User;
import LinkedInManagementSystem.example.LinkedInManagement.ResponseObjects.RequestToAddPost;
import LinkedInManagementSystem.example.LinkedInManagement.SeriveLayer.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostContoller {

    @Autowired
    private PostService postService;


    @PostMapping("/addPost")
    private String addPost(@RequestBody RequestToAddPost requestToAddPost) throws Exception{


        String response = postService.addPostByUser(requestToAddPost);

        return response;

    }

    @PostMapping("/addPostCompany")
    private String addPostByCompany(@RequestBody RequestToAddPost requestToAddPost) throws Exception{
       try {
           String response = postService.addPostByCompany(requestToAddPost);
           return response;
       }
       catch (Exception e){
           return e.getMessage();
       }
    }

    // all posts by all users
    @GetMapping("/allPosts")
    public ResponseEntity allPosts() throws Exception{

        try
        {
            List<Post> allPosts = postService.allPostsByUser();
            return new ResponseEntity(allPosts , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage() ,HttpStatus.BAD_REQUEST);
        }
    }

    // all posts by all companies

    @GetMapping("/allPostsByCompany")
public ResponseEntity allPostsByCompany() throws Exception{

        try {
            List<Post> postList = postService.allPostsByCompany();
            return new ResponseEntity(postList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete-post")
    public ResponseEntity deletePost(Integer postId) throws Exception{

        try {
            return new ResponseEntity(postService.deletePost(postId) , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    // find all users who like the post
    @GetMapping("/postLikedUsers/{postId}")
    public ResponseEntity postLikedUsers(@PathVariable("postId") Integer postId) throws Exception{

        try {
            List<User> userList = postService.postLikedUser(postId);
            return new ResponseEntity(userList , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    // find all the posts liked by the user
    @GetMapping("/likedPosts/{userId}")
    public ResponseEntity allLikedPostsByUsers(@PathVariable("userId")Integer userId) throws Exception{
        try {

            List<Post> postList = postService.allLikedPostsByUsers(userId);
            return new ResponseEntity(postList , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    // get all the users who are liked my all posts
    @GetMapping("/allUsersWhoLikedMyPosts")
    public ResponseEntity allUsersWhoLikedMyPosts(@RequestParam("userId") Integer userId) throws Exception{
        try {
            List<User> userList = postService.allUsersWhoLikedMyPosts(userId);
            return new ResponseEntity(userList , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    // see all the comments on this post
    @GetMapping("/allComments")
    public ResponseEntity allComments(@RequestParam("postId") Integer postId) throws  Exception{
        try {
            List<String> comments = postService.allComments(postId);

            return new ResponseEntity(comments , HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

}
