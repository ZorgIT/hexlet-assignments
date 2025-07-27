package exercise.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

// BEGIN
@Getter
@Setter
public class GuestCreateDTO {

    @NotBlank
    private String name;

    @Email
    private String email;

    @Size(min = 11, max = 13)
    @Pattern(regexp = "^\\+[0-9]+$")
    private String phoneNumber;

    @Size(min=4, max =4)
    private String clubCard;

    @FutureOrPresent
    private LocalDate cardValidUntil;

    @CreatedDate
    private LocalDate createdAt;
}
// END
