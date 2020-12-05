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

    @Override
    public int compareTo(AdForm that) {
        return Long.compare(this.creationTimestamp, that.creationTimestamp);
    }

    @Override
    public String toString(){
        return String.format("AdForm: id:%d, title:%s, content:%s, time:%d", id, title, content, creationTimestamp);
    }
}
