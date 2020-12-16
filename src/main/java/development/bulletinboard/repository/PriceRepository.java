package development.bulletinboard.repository;

import development.bulletinboard.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий типа валюты
 */
@Repository
public interface PriceRepository extends JpaRepository<Price, String> {
}
