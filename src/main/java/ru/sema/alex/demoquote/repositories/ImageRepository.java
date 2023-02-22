package ru.sema.alex.demoquote.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sema.alex.demoquote.models.imagemodels.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {


}
