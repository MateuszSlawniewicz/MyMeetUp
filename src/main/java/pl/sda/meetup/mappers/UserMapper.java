package pl.sda.meetup.mappers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.meetup.dao.model.User;
import pl.sda.meetup.dto.UserLoginDto;

@Component
public class UserMapper {


    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public User fromUserLoginDtoToUser(UserLoginDto userLoginDto) {
        return User.builder()
                .email(userLoginDto.getEmail())
                .name(userLoginDto.getEmail())
                .password(passwordEncoder.encode(userLoginDto.getPassword()))
                .build();
    }

    public UserLoginDto fromUserToUserLoginDto(User user) {
        return UserLoginDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

}
