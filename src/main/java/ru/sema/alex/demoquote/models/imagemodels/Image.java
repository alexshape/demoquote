package ru.sema.alex.demoquote.models.imagemodels;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;
import ru.sema.alex.demoquote.models.imagemodels.ImageOwner;

import javax.persistence.*;
import java.io.IOException;

@Entity
@Table(name="images")
public class Image{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "originalFileName")
    private String originalFileName;

    @Column(name = "size")
    private Long size;

    @Column(name = "contentType")
    private String contentType;

    @Column(name = "isActive")
    private boolean isActive;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Lob
    @Column(name = "bytes", columnDefinition="BLOB")
    private byte[] bytes;

    public ImageOwner getOwner() {
        return owner;
    }

    public void setOwner(ImageOwner owner) {
        this.owner = owner;
        owner.attachImage(this);
    }

    @OneToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY)
    private ImageOwner owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public static Image getImageFromMultipartFile(MultipartFile file){

        Image photo =null;

        if(file.getSize() != 0){

            photo = new Image();
            photo.setActive(true);
            photo.setName(file.getName());
            photo.setOriginalFileName(file.getOriginalFilename());
            photo.setSize(file.getSize());
            photo.setContentType(file.getContentType());
            try {
                photo.setBytes(file.getBytes());
            } catch (IOException e) {
                //to do
            }
        }

        return photo;
    }

    public static void saveImageToImageOwner(JpaRepository imageRepository, ImageOwner imageOwner, Image image){

        if(image != null) {
            image.setOwner(imageOwner);
            imageRepository.save(image);
        }
    };


}
