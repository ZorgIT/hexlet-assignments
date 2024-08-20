package exercise.dto.users;

import exercise.model.User;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

// BEGIN
@Getter
@AllArgsConstructor // Add this to generate a constructor with parameters
public class UsersPage {
    private List<User> users; // Declare a field for the user list
}
// END
