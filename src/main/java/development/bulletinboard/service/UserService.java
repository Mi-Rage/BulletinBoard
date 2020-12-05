package development.bulletinboard.service;

import development.bulletinboard.model.Role;
import development.bulletinboard.model.User;
import development.bulletinboard.repository.RoleRepository;
import development.bulletinboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис пользователей
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    UserService(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * Сохраняем пользователя в БД.
     * Если пользователь уже есть - не сохраняем.
     * Если пользователь новый - добавляем к тексту пароля из веб-формы префикс {noop}
     * Все новые пользователи пока будут с правами USER.
     * @param user - объект пользователя для сохранения
     * @return - true если пользователь уникальный, false - если такой пользователь есть
     */
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

    /**
     * Поиск в репозитории всех пользователей
     * @return список всех пользователей
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Поиск в репозитории всех ролей пользователей
     * @return список пользователей и их ролей
     */
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
