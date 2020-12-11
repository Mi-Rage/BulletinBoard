package development.bulletinboard.repository;

import development.bulletinboard.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репоизторий категорий объявления
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
