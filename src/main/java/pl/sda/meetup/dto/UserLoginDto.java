package pl.sda.meetup.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ToString(exclude = "password")
public class UserLoginDto {
    @NotBlank(message = "not blank")
    @NotNull(message = "not null")
    @Size(min = 1, max = 50, message
            = "Name must be between 10 and 200 characters")
    private String name;


    @Email(message = "Email should be valid")
    private String email;

    @NotBlank
    @Size(min = 8, max = 30, message
            = "Password must be between 8 and 30 characters")
    private String password;




}
