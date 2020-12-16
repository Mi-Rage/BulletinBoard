package development.bulletinboard.repository;

import development.bulletinboard.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репоизторий категорий объявления
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    /**
     * Поиск объекта категории по полю её имени
     * @param categoryName - String, имя категории
     * @return Category, объект категории из БД
     */
    Category findByCategoryName(String categoryName);
}
