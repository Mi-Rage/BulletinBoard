package development.bulletinboard.service;

import development.bulletinboard.model.Role;
import development.bulletinboard.model.User;
import development.bulletinboard.repository.RoleRepository;
import development.bulletinboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    UserService(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public boolean saveUser(User user) {
        Optional<User> userFromDB = userRepository.findById(user.getUserName());

        if (userFromDB.isPresent()) {
            return false;
        }

        user.setPassword("{noop}" + user.getPassword());
        userRepository.save(user);
        Role role = new Role (user.getUserName(), "ROLE_USER");
        roleRepository.save(role);

        return true;
    }
}
