package ru.sema.alex.demoquote.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.sema.alex.demoquote.models.Quote;

import java.util.List;

public interface QuotesRepository extends JpaRepository<Quote, Long> {

    List<Quote> findByTitle(String title);

    @Query(value = "SELECT * FROM QUOTES ORDER BY CREATION_DATE DESC LIMIT 1", nativeQuery = true)
    Quote findLastQuote();

    @Query(value = "SELECT * FROM QUOTES ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Quote findRandomQoute();



}
