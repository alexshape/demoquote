package ru.sema.alex.demoquote.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sema.alex.demoquote.models.imagemodels.Image;
import ru.sema.alex.demoquote.repositories.ImageRepository;

@Controller
@RequestMapping("/images")
public class ImageController {

    @Autowired
    ImageRepository imageRepository;

    public ImageController(ImageRepository imageRepository) {
    }

    @GetMapping("/{id}")
    public ResponseEntity getImage(@PathVariable Long id) {

        Image image = imageRepository.findById(id).orElse(null);

        if(image != null){
            return ResponseEntity.ok(image.getBytes());
        }

        return ResponseEntity.ok(null);

    }
}
