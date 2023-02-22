package ru.sema.alex.demoquote.models.imagemodels;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@DiscriminatorValue("ImageOwnerList")
public class ImageOwnerList extends ImageOwner {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Image> images;

    public ImageOwnerList() {
        this.images = new ArrayList<>();
    }

    public ImageOwnerList(List<Image> images) {
        this.images = images;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public void attachImage(Image image) {
        images.add(image);
        setMainImage(image);
    }
}
