package pl.sda.meetup.dto;

import lombok.*;
import pl.sda.meetup.dao.model.Role;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String name;
    private String email;
    private Set<Role> roles;


}
