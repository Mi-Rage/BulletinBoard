package development.bulletinboard.repository;

import development.bulletinboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий пользователей
 */
public interface UserRepository extends JpaRepository<User,String> {
}
