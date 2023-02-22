package ru.sema.alex.demoquote.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sema.alex.demoquote.models.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {


}
