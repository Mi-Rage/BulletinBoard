package development.bulletinboard.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Класс пользователей.
 * В БД значения 'username' этой таблицы связаны с значениями 'username' таблицы ролей
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username")
    @NotBlank
    @Size(min = 3, max = 50)
    private String userName;

    @NotBlank
    @Column(name = "password")
    @Size(min = 8, max = 20)
    private String password;

    @Transient
    private String passwordConfirm;

    @Column(name = "enabled")
    private boolean isEnabled = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdForm> adFormList;

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public List<AdForm> getAdFormList() {
        return adFormList;
    }

    public void setAdFormList(List<AdForm> adFormList) {
        this.adFormList = adFormList;
    }
}
