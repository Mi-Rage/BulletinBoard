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
        long counter = lastIdRepository() + 1;
        adForm.setId((int) counter);
        repository.save(adForm);
        System.out.println(adForm.toString());
    }

    public int lastIdRepository(){
        return (int) repository.count();
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
