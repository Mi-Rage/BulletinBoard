package development.bulletinboard.service;

import development.bulletinboard.model.AdForm;
import development.bulletinboard.repository.AdFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class AdFormService {

    private final AdFormRepository repository;

    @Autowired
    public AdFormService(AdFormRepository repository) {
        this.repository = repository;
    }

    public void saveForm(AdForm adForm) {
        int counter = lastIdRepository() + 1;
        adForm.setId(counter);
        repository.save(adForm);
        System.out.println(adForm.toString());
    }

    public int lastIdRepository(){
        return (int) repository.count();
    }

    public AdForm getAdFormById(int id) {
        return repository.findById(id).get();
    }
    public List<AdForm> getLastAdForms() {
        return StreamSupport
                .stream(
                        Spliterators.spliteratorUnknownSize(repository.findAll().iterator(), Spliterator.NONNULL),
                        false)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}
