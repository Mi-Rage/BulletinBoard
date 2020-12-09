package development.bulletinboard.service;

import development.bulletinboard.model.AdForm;
import development.bulletinboard.model.User;
import development.bulletinboard.repository.AdFormRepository;
import development.bulletinboard.repository.UserRepository;
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

    @Autowired
    public AdFormService(AdFormRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
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
        return repository.getOne(id);
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
        return repository.findAllByTitleContainsOrContentContainsOrderByIdDesc(text, text);
    }

    /**
     * Поиск объявлений по запрошенному пользователю
     * @param userName - String имя пользователя, чьи объявы ищем
     * @return List список объявлений этого пользователя
     */
    public List<AdForm> getAllAdsFromUser(String userName) {
        if (userRepository.findById(userName).isPresent()) {
            User user = userRepository.findById(userName).get();
            return repository.findAllByUserOrderByIdDesc(user);
        } else {
            throw new RuntimeException("Нет такого пользователя " + userName);
        }
    }
}
