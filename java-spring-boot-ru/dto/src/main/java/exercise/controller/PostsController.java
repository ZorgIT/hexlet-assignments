package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public ResponseEntity<List<PostDTO>> showPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTOs = new ArrayList<>();
        for (Post post : posts) {
            PostDTO postDTO = new PostDTO();

            postDTO.setId(post.getId());
            postDTO.setTitle(post.getTitle());
            postDTO.setBody(post.getBody());
            List<Comment> comments = commentRepository.findByPostId(post.getId());
            ArrayList<CommentDTO> commentDTOs = new ArrayList<>();
            for (Comment comment : comments) {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setId(comment.getId());
                commentDTO.setBody(comment.getBody());
                commentDTOs.add(commentDTO);
            }
            postDTO.setComments(commentDTOs);
            postDTOs.add(postDTO);
        }
        return new ResponseEntity<>(postDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> showPost(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElse(null);
        PostDTO postDTO = new PostDTO();
        if (post == null) {
            throw new ResourceNotFoundException("Post with id " + id + " not found");
        } else {
            postDTO.setId(post.getId());
            postDTO.setTitle(post.getTitle());
            postDTO.setBody(post.getBody());
            List<Comment> comments = commentRepository.findByPostId(post.getId());
            ArrayList<CommentDTO> commentDTOs = new ArrayList<>();
            for (Comment comment : comments) {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setId(comment.getId());
                commentDTO.setBody(comment.getBody());
                commentDTOs.add(commentDTO);
            }
            postDTO.setComments(commentDTOs);
        }
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

}
// END
