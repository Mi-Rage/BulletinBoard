package development.bulletinboard.service;

import development.bulletinboard.model.AdForm;
import development.bulletinboard.repository.AdFormRepository;
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

    @Autowired
    public AdFormService(AdFormRepository repository) {
        this.repository = repository;
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
    public List<AdForm> geiAdFormBySearch(String text) {
        return repository.findAllByTitleContainsOrContentContainsOrderByIdDesc(text, text);
    }
}
