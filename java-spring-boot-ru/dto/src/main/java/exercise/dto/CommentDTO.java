package exercise.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

// BEGIN
@Getter
@Setter
public class CommentDTO {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String body;
}
// END
