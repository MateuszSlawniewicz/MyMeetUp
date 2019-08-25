package pl.sda.meetup.services;

import org.springframework.stereotype.Service;
import pl.sda.meetup.dao.repositories.UserRepository;
import pl.sda.meetup.dto.UserLoginDto;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserLoginDto saveUser(UserLoginDto userLoginDto) {
        return null;
    }
}
