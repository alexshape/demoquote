package ru.sema.alex.demoquote.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "scores")
public class Score {

    public Score() {
    }

    public void markAsLike(){
        this.type = ScoreType.like;
    }

    public void markAsDisLike(){
        this.type = ScoreType.DisLike;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public ScoreType getType() {
        return type;
    }

    public void setType(ScoreType type) {
        this.type = type;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    private ScoreType type;

    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "quote", referencedColumnName = "id")
    private Quote quote;

    @Column(name = "date")
    private LocalDateTime date;

    public Score(Quote quote) {
        this.date = LocalDateTime.now();
        this.quote = quote;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
