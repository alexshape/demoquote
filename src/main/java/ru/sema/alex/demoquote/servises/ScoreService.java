package ru.sema.alex.demoquote.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sema.alex.demoquote.models.Quote;
import ru.sema.alex.demoquote.models.Score;
import ru.sema.alex.demoquote.repositories.ScoreRepository;

@Service
public class ScoreService {

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    private QuoteService quoteService;

    public void likeQuote(Quote quote){

        Score scope = new Score(quote);
        scope.markAsLike();

        saveScore(scope);

    }

    public void disLikeQuote(Quote quote){

        Score scope = new Score(quote);
        scope.markAsDisLike();

        saveScore(scope);

    }

    private void saveScore(Score score){

        scoreRepository.save(score);

        //score.getQuote().getScores().add(score);
        quoteService.saveQuote(score.getQuote());
    }


}
