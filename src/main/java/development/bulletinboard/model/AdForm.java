package development.bulletinboard.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Класс объявления.
 */
@Entity
@Table(name = "adform")
public class AdForm implements Serializable, Comparable<AdForm> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "timestamp")
    private long creationTimestamp;

    @ManyToOne()
    @JoinColumn(name = "username")
    private User user;

    @Column(name = "category_id")
    private Long categoryId;

    @Transient
    private String normalDate;
    @Transient
    private String categoryName;

    public AdForm() {
        this.creationTimestamp = System.currentTimeMillis();
    }

    public AdForm(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationTimestamp = System.currentTimeMillis();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getNormalDate() {
        return normalDate;
    }

    public void setNormalDate(String normalDate) {
        this.normalDate = normalDate;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public int compareTo(AdForm that) {
        return Long.compare(this.creationTimestamp, that.creationTimestamp);
    }

    @Override
    public String toString(){
        return String.format("Added : id:%d, title:%s, content:%s, time:%d, user:%s",
                id, title, content, creationTimestamp, user.getUserName());
    }
}
