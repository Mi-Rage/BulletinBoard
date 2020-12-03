package development.bulletinboard.service;

import development.bulletinboard.model.AdForm;
import development.bulletinboard.repository.AdFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class AdFormService {

    private final AdFormRepository repository;

    @Autowired
    public AdFormService(AdFormRepository repository) {
        this.repository = repository;
    }

    public void saveForm(AdForm adForm) {
        repository.save(adForm);
        System.out.println(adForm.toString());
    }

    public AdForm getAdFormById(int id) {
        return repository.getOne(id);
    }

    public List<AdForm> getLastAdForms() {
        return StreamSupport
                .stream(
                        Spliterators.spliteratorUnknownSize(repository.findAll().iterator(), Spliterator.NONNULL),
                        false)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public List<AdForm> geiAdFormBySearch(String text) {
        return repository.findAllByTitleContainsOrContentContainsOrderByIdDesc(text, text);
    }
}
