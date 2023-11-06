package LinkedInManagementSystem.example.LinkedInManagement.SeriveLayer;

import LinkedInManagementSystem.example.LinkedInManagement.Exceptions.*;
import LinkedInManagementSystem.example.LinkedInManagement.Models.*;
import LinkedInManagementSystem.example.LinkedInManagement.Repository.CompanyRepository;
import LinkedInManagementSystem.example.LinkedInManagement.Repository.PostRepository;
import LinkedInManagementSystem.example.LinkedInManagement.Repository.UserRepository;
import LinkedInManagementSystem.example.LinkedInManagement.ResponseObjects.RequestToAddPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

   public String addPostByUser(RequestToAddPost requestToAddPost) throws Exception{

       Integer personId= requestToAddPost.getPostingPersonId();

       User user = userRepository.findById(personId).get();


       // set attributes

       Post post = Post.builder()
                   .content(requestToAddPost.getContent())
                   .comment(requestToAddPost.getComment())
                   .repost(requestToAddPost.getRepost())
                   .localDate(requestToAddPost.getLocalDate())
                   .name(user.getUserName())
                   .build();


       // set entities

       post.setUser(user);
       user.getPostList().add(post);
       userRepository.save(user);
       postRepository.save(post);

       return "the post added successfully ! keep posting and motivating others";
   }
    public String addPostByCompany(RequestToAddPost requestToAddPost) throws Exception{

        Integer personId= requestToAddPost.getPostingPersonId();

        Company company = companyRepository.findById(personId).get();

        if(company == null) {
            throw new CompanyIdIsInvalid("company id is invalid");
        }
        // set attributes

        Post post = Post.builder()
                .content(requestToAddPost.getContent())
                .comment(requestToAddPost.getComment())
                .name(company.getName())
                .localDate(requestToAddPost.getLocalDate())
                .repost(requestToAddPost.getRepost()).
                build();


        // set entities

        post.setCompany(company);
        company.getPostList().add(post);
        companyRepository.save(company);
        postRepository.save(post);

        return "the post added successfully ! keep posting and motivating others";
    }

    public List<Post> allPostsByUser() throws Exception{

        List<Post> allPosts = postRepository.findAll();

       List<Post> allPostByUser = new ArrayList<>();

       for(Post post: allPosts) {

           if(post.getUser() != null) allPostByUser.add(post);
       }

       if(allPostByUser.isEmpty()) {
           throw new NoPostsAddedByUser(" no posts add by users");
       }

       return allPostByUser;
}

    public List<Post> allPostsByCompany() throws Exception{

        List<Post> allPosts = postRepository.findAll();

        List<Post> allPostByCompany = new ArrayList<>();

        for(Post post: allPosts) {

            if(post.getCompany() != null) allPostByCompany.add(post);
        }

        if(allPostByCompany.isEmpty()) {
            throw new NoPostsAddedByCompany(" no posts add by companies");
        }

        return allPostByCompany;
    }
    public String  deletePost(Integer postId) throws Exception{

        Optional<Post> optionalPost = postRepository.findById(postId);

        if(!optionalPost.isPresent()) {
            throw new PostNotFounded("post not found");
        }

        postRepository.delete(optionalPost.get());

        return "post with  post id " + postId + " is deleted successfully";

    }

    public List<User> postLikedUser(Integer postId) throws Exception {

        Optional<Post> optionalPost = postRepository.findById(postId);

        if(!optionalPost.isPresent()){
            throw new PostNotFounded("post id is invalid");
        }

          Post post = optionalPost.get();
        List<User> userList = new ArrayList<>();

       List<Like> likeList =  post.getLikeList();

       for(Like like : likeList) {

           userList.add(like.getUser()); //add the liked user
       }

       return  userList;
    }
    public List<Post> allLikedPostsByUsers(Integer userId)throws Exception{

       Optional<User> userOptional = userRepository.findById(userId);

       if(!userOptional.isPresent()){
           throw new UserIdIsInvalid("userId is not valid");
       }

       User user = userOptional.get();

       List<Like> likeList = user.getLikeList();

       List<Post> postList = new ArrayList<>();

       for(Like like : likeList) {

           postList.add(like.getPost());
       }

       if(postList.isEmpty()){
           throw new NotLikedAnyPostsTillThis("your not liked any posts till this point ! Start encourage others now");
       }

       return postList;
    }

    public List<User> allUsersWhoLikedMyPosts(Integer userId) throws Exception{

       Optional<User> optionalUser = userRepository.findById(userId);

        if(!optionalUser.isPresent()){
            throw new UserIdIsInvalid("userId is not valid");
        }

        User user = optionalUser.get();

        List<Post> postList = user.getPostList();

        List<User> userList = new ArrayList<>();

        for(Post post : postList) {

            // each post liked by multiple users

            for(Like like : post.getLikeList()){

                userList.add(like.getUser());
            }
        }

        if(userList.isEmpty()){
            throw new NotLikedAnyPostsTillThis("sorry my dear user , Your posts didn't get any Likes still this point of time!" +
                    "Try to upload some interesting things");
        }

        return userList;
    }

    public List<String> allComments(Integer postId) throws Exception{

        Optional<Post> optionalPost = postRepository.findById(postId);

        if(!optionalPost.isPresent()){
            throw new PostNotFounded("post id is invalid");
        }

        Post post = optionalPost.get();

        List<Comment> comments = post.getComments();

        List<String> commentsList = new ArrayList<>();

        for(Comment comment : comments){

            commentsList.add(comment.getMessage() + " commented by "+ comment.getUser().getUserName());
        }

        if(commentsList.isEmpty()){
            throw new NoCommentsGotYet("no comments");
        }

        return commentsList;
    }

}
