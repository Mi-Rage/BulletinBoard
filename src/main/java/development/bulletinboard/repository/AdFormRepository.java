package development.bulletinboard.repository;

import development.bulletinboard.model.AdForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AdFormRepository extends JpaRepository<AdForm, Integer> {

    List<AdForm> findAllByTitleContainsOrContentContainsOrderByIdDesc(String title, String content);

}
