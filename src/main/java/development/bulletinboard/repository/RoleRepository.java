package development.bulletinboard.repository;

import development.bulletinboard.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий ролей пользователей
 */
public interface RoleRepository extends JpaRepository<Role, String> {
}
