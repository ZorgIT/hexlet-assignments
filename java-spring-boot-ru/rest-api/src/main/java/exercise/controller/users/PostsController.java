package exercise.controller.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api/users")
public class PostsController {

    private List<Post> posts = new ArrayList<>();

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable long id) {
        List<Post> userPosts = posts.stream()
                .filter(p -> p.getUserId() == id)
                .toList();

        return ResponseEntity.ok()
                .body(userPosts);
    }

    @PostMapping("/{id}/posts")
    public ResponseEntity<Post> createUserPost(
            @PathVariable int id,
            @RequestBody Map<String, String> postData) {

        if (!postData.containsKey("slug") ||
                !postData.containsKey("title") ||
                !postData.containsKey("body")) {
            return ResponseEntity.badRequest().build();
        }

        Post newPost = new Post();
        newPost.setUserId(id);
        newPost.setSlug(postData.get("slug"));
        newPost.setTitle(postData.get("title"));
        newPost.setBody(postData.get("body"));

        posts.add(newPost);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newPost);
    }
}
// END
