package pl.sda.meetup.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.meetup.dao.model.User;
import pl.sda.meetup.dao.repositories.UserRepository;
import pl.sda.meetup.dto.UserLoginDto;
import pl.sda.meetup.mappers.UserMapper;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserLoginDto saveUser(UserLoginDto userLoginDto) {
        User save = userRepository.save(userMapper.fromUserLoginDtoToUser(userLoginDto));
        log.info(save.getEmail());
        log.info(save.getPassword());
        log.info(save.getId().toString());
        return userMapper.fromUserToUserLoginDto(save);
    }
}
