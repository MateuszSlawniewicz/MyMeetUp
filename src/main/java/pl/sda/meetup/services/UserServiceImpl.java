package pl.sda.meetup.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.meetup.dao.model.Role;
import pl.sda.meetup.dao.model.User;
import pl.sda.meetup.dao.repositories.RoleRepository;
import pl.sda.meetup.dao.repositories.UserRepository;
import pl.sda.meetup.dto.UserLoginDto;
import pl.sda.meetup.mappers.UserMapper;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserLoginDto saveUser(UserLoginDto userLoginDto) {
        Role role = new Role();
        role.setName("RANDOM_USER");
        Role savedRole = roleRepository.save(role);
        Set<Role> roles = new HashSet<>();
        roles.add(savedRole);
        userLoginDto.setRoles(roles);
        User save = userRepository.save(userMapper.fromUserLoginDtoToUser(userLoginDto));
        log.info(save.getEmail());
        log.info(save.getPassword());
        log.info(save.getId().toString());
        save.getRoles().forEach(e -> log.info(e.getName()));
        return userMapper.fromUserToUserLoginDto(save);
    }
}
