package ru.sema.alex.demoquote.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.sema.alex.demoquote.models.imagemodels.Image;
import ru.sema.alex.demoquote.models.imagemodels.ImageOwnerList;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "quotes")
public class Quote{


    public ImageOwnerList getImageOwner() {
        return imageOwner;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="imageOwner", referencedColumnName = "id")
    private ImageOwnerList imageOwner = new ImageOwnerList();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", columnDefinition = "text")
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "data", columnDefinition = "text")
    private String data;
    private Date creationDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "author", referencedColumnName = "id")
    private User author;

    public Integer getScore() {
        return score;
    }


    public void setScore(Integer score) {
        this.score = score;
    }

    @Column(name = "score")
    private Integer score = 0;

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Score> scores = new ArrayList<>();

    public Image getMainPhoto() {

        List<Image> images = imageOwner.getImages();
        if(images != null){
            for (Image image:images) {
                if(image.getId().equals(imageOwner.getMainImageId()))
                    return image;
            }
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getData() {
        return data;
    }

    public Date getCreationDate() {
        return creationDate;
    }
    public User getAuthor() {
        return author;
    }

    public Quote() {

    }

    public Quote(String title, String description, String data, User author) {

        this.title = title;
        this.description = description;
        this.author = author;
        this.data = data;
        this.creationDate = new Date();

        if(this.description.equals("")){
            if(data.length() > 575)
                this.description = data.substring(0, 575) + "...";
            else
                this.description = data;
        }
    }

    public Quote(long id, String title, String description, String data, User author) {
        this(title, description, data, author);
        this.id = id;
    }

}
