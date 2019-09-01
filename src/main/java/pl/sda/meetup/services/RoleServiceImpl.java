package pl.sda.meetup.services;

import pl.sda.meetup.dao.model.Role;
import pl.sda.meetup.dao.repositories.RoleRepository;

public class RoleServiceImpl implements RoleService {


    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
