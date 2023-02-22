package ru.sema.alex.demoquote.models.imagemodels;

import javax.persistence.*;

@Entity
@Table(name ="imageowners")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="DISCRIMINATOR", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("ImageOwner")
public abstract class ImageOwner {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "mainImageId")
    private Long mainImageId;

    public Long getMainImageId() {
        return mainImageId;
    }

    public boolean hasMainImgage() {
        return mainImageId != null;
    }

    public void setMainImage(Image image){
        this.mainImageId = image.getId();
    };

    public abstract void attachImage(Image image);

}
