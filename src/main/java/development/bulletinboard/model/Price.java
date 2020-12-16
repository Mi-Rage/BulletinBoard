package development.bulletinboard.model;

import javax.persistence.*;
import java.util.List;

/**
 * Класс типов валюты
 */
@Entity
@Table(name = "price")
public class Price {

    @Id
    @Column(name = "price_id")
    private String priceId;

    @Column(name = "price_name")
    private String priceName;

    @OneToMany(mappedBy = "price", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdForm> adFormList;

    public Price() {
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String priceName) {
        this.priceName = priceName;
    }

    public List<AdForm> getAdFormList() {
        return adFormList;
    }

    public void setAdFormList(List<AdForm> adFormList) {
        this.adFormList = adFormList;
    }
}
