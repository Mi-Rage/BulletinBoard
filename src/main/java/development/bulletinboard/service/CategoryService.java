package development.bulletinboard.service;

import development.bulletinboard.model.Category;
import development.bulletinboard.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис категорий объявлений
 */
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Получаем список всех категорий
     * @return List, список категорий из репозитория
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public String getCategoryNameById(Long id) {
        if (categoryRepository.findById(id).isPresent()) {
            return categoryRepository.findById(id).get().getCategoryName();
        } else {
            throw new RuntimeException("Такой категории нет! " + id);
        }
    }

}
