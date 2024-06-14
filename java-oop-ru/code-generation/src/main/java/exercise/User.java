package exercise;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

// BEGIN
@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
// END
class User {
    int id;
    String firstName;
    String lastName;
    int age;
}
