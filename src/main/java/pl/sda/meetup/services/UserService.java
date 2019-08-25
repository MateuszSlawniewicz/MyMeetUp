package pl.sda.meetup.services;

import pl.sda.meetup.dto.UserLoginDto;

public interface UserService {
    UserLoginDto saveUser(UserLoginDto userLoginDto);

}
