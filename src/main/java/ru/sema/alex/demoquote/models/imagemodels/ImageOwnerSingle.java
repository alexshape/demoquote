package ru.sema.alex.demoquote.models.imagemodels;


import javax.persistence.*;

@Entity
@DiscriminatorValue("ImageOwnerSingle")
public class ImageOwnerSingle extends ImageOwner{

    public ImageOwnerSingle() {

    }
    public ImageOwnerSingle(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id", referencedColumnName = "owner_id")
    private Image image;

    @Override
    public void attachImage(Image image) {
        this.image = image;
        setMainImage(this.image);
    }
}
