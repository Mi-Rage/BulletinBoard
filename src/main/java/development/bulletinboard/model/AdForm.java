package development.bulletinboard.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class AdForm implements Serializable, Comparable<AdForm> {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;
    @Column
    private String content;
    @Column
    private final long creationTimestamp;

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

    @Override
    public int compareTo(AdForm that) {
        return Long.compare(this.creationTimestamp, that.creationTimestamp);
    }

    @Override
    public String toString(){
        return this.id + " / " + this.title + " / " + this.content;
    }
}
