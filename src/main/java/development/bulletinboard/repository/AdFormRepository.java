package development.bulletinboard.repository;

import development.bulletinboard.model.AdForm;
import development.bulletinboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий объявлений
 */
public interface AdFormRepository extends JpaRepository<AdForm, Integer> {

    /**
     * Метод поиска в БД по объявлениям.
     * Ищем вхождение подстроки в названиях или содержании объявления,
     * добавляем результаты в список, сотрированный по ID в порядке убывания (новые выше)
     * @param title - искать в заголовке
     * @param content - искать в содержимом
     * @return - список объеков объявлений с входжениями
     */
    List<AdForm> findAllByTitleContainsOrContentContainsOrderByIdDesc(String title, String content);

    /**
     * Получение списка всех объявлений от конкретного пользователя
     * @param user - User, пользователь по которому ищем его объявления
     * @return List, список объявлений в порядке убывания (новые выше)
     */
    List<AdForm> findAllByUserOrderByIdDesc(User user);

}
