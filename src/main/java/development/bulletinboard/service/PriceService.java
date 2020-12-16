package development.bulletinboard.service;

import development.bulletinboard.model.Price;
import development.bulletinboard.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис типов валюты
 */
@Service
public class PriceService {

    private final PriceRepository priceRepository;

    @Autowired
    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    /**
     * Получаем список всех доступных валют
     * @return List, список всех валют
     */
    public List<Price> getAllPrices() {
        return priceRepository.findAll();
    }
}
