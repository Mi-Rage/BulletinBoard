package development.bulletinboard.service;

import development.bulletinboard.model.AdForm;
import development.bulletinboard.model.Category;
import development.bulletinboard.model.User;
import development.bulletinboard.repository.AdFormRepository;
import development.bulletinboard.repository.CategoryRepository;
import development.bulletinboard.repository.UserRepository;
import development.bulletinboard.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Сервис объявлений
 */
@Service
public class AdFormService {

    private final AdFormRepository repository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public AdFormService(AdFormRepository repository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Сохранение объявления в БД
     * @param adForm - объект объявления
     */
    public void saveForm(AdForm adForm) {
        repository.save(adForm);
        System.out.println(adForm.toString());
    }

    /**
     * Получим объявление по его ID
     * @param id - ID объявления
     * @return - объект обявления с этим ID
     */
    public AdForm getAdFormById(int id) {
        AdForm adForm = repository.getOne(id);
        getAdvanced(adForm);
        return adForm;
    }

    /**
     * Список объявлений, сотрированный по дате создания (новые - выше)
     * @return - список объектов AdForm
     */
    public List<AdForm> getLastAdForms() {
        return StreamSupport
                .stream(
                        Spliterators.spliteratorUnknownSize(repository.findAll().iterator(), Spliterator.NONNULL),
                        false)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    /**
     * Поиск объявлений по заданному тексту
     * @param text - текст из веб-формы поиска
     * @return список объявлений с вхождением текста
     */
    public List<AdForm> getAdFormBySearch(String text) {
        List<AdForm> adFormList = repository.findAllByTitleContainsOrContentContainsOrderByIdDesc(text, text);
        for(AdForm eachAd : adFormList) {
            getAdvanced(eachAd);
        }
        return adFormList;
    }

    /**
     * Поиск объявлений по запрошенному пользователю
     * @param userName - String имя пользователя, чьи объявы ищем
     * @return List список объявлений этого пользователя
     */
    public List<AdForm> getAllAdsFromUser(String userName) {
        List<AdForm> adFormList;
        if (userRepository.findById(userName).isPresent()) {
            User user = userRepository.findById(userName).get();
            adFormList = repository.findAllByUserOrderByIdDesc(user);
        } else {
            throw new RuntimeException("Нет такого пользователя " + userName);
        }

        for(AdForm eachAd : adFormList) {
            getAdvanced(eachAd);
        }
        return adFormList;
    }

    /**
     * Поиск списка объявлений в заданной категории
     * @param categoryName - String, имя категории к которой ищем объявы
     * @return List список объявлений из этой категории
     */
    public List<AdForm> getAllAdsFromCategory(String categoryName) {
        List<AdForm> adFormList;
        Category category = categoryRepository.findByCategoryName((categoryName));
        adFormList = repository.findAllByCategoryIdOrderByIdDesc(category.getId());
        for(AdForm eachAd : adFormList) {
            getAdvanced(eachAd);
        }
        return adFormList;
    }

    /**
     * Дополняет объект класса AdForm значениями полей
     * нормальной даты и наименованием категории
     * @param adForm обновляемый объект объявления
     */
    private void getAdvanced(AdForm adForm) {
        String normalDate = Util.getTimeFromStamp(adForm.getCreationTimestamp());
        adForm.setNormalDate(normalDate);
        Optional<Category> category = categoryRepository.findById(adForm.getCategoryId());
        if (category.isPresent()) {
            String categoryName = category.get().getCategoryName();
            adForm.setCategoryName(categoryName);
        } else {
            throw new RuntimeException("Нет такой категории " + adForm.getCategoryId());
        }
    }

    /**
     * Получаем значение цены для выбранного объявления
     * @param adForm - выбранное объявление
     * @return String для использования в model
     */
    public String getPriceValue(AdForm adForm) {
        return adForm.getPriceValue() == 0f ? "Не определена" : adForm.getPriceValue().toString();
    }

    /**
     * Получаем значение типа вылюты для выбранного объявления
     * @param adForm - выбранное объявление
     * @return String для использования в model
     */
    public String getPriceType(AdForm adForm) {
        return adForm.getPriceValue() == 0f ? "" : adForm.getPrice().getPriceId();
    }
}
