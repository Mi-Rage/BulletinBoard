package development.bulletinboard.repository;

import development.bulletinboard.model.AdForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdFormRepository extends JpaRepository<AdForm, Integer> {
}
