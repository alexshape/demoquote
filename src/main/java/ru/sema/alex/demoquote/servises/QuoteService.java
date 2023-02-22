package ru.sema.alex.demoquote.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.sema.alex.demoquote.models.Score;
import ru.sema.alex.demoquote.models.ScoreType;
import ru.sema.alex.demoquote.models.User;
import ru.sema.alex.demoquote.models.imagemodels.Image;
import ru.sema.alex.demoquote.models.Quote;
import ru.sema.alex.demoquote.models.imagemodels.ImageOwner;
import ru.sema.alex.demoquote.repositories.ImageRepository;
import ru.sema.alex.demoquote.repositories.QuotesRepository;
import ru.sema.alex.demoquote.repositories.UserRepository;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuoteService {

    @Autowired
    private QuotesRepository quotesRepository;
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    Random random;

    List<Quote> quotes = new ArrayList<>();

    public QuoteService(QuotesRepository quotesRepository, ImageRepository imageRepository) {
        random = new Random();
    }

    public List<Quote> list() {
        return quotesRepository.findAll();
    }

    public List<Quote> list(String title) {

        if(title != null)
            return quotesRepository.findByTitle(title);

        return list();
    }


    public void saveQuote(Quote quote, MultipartFile firstPhoto, MultipartFile secondPhoto){

        Image.saveImageToImageOwner(imageRepository, quote.getImageOwner(), Image.getImageFromMultipartFile(firstPhoto));
        Image.saveImageToImageOwner(imageRepository, quote.getImageOwner(), Image.getImageFromMultipartFile(secondPhoto));

        saveQuote(quote);
    }

    public void saveQuote(Quote quote){

        //score calc
        Integer newCcore = 0;

        for (Score score:quote.getScores()) {
            if (score.getType() == ScoreType.like)
                newCcore++;
            else
                newCcore--;
        }
        quote.setScore(newCcore);

        quotesRepository.save(quote);

        User user = quote.getAuthor();
        List<Quote> quotes = user.getQuotes();
        Quote quoteFinded = null;
        for (Quote quoteP: quotes) {
          if(quote == quoteP){
              quoteFinded = quoteP;
              break;
          }
        }

        if(quoteFinded == null) {
            quotes.add(quote);
            userRepository.save(user);
        }
    }

    public void deleteQuote(Long id){
        quotesRepository.deleteById(id);
    }

    public Quote getQuote(Long id){
        return quotesRepository.findById(id).orElse(null);
    }

    public Quote getLastQuote() {

        return quotesRepository.findLastQuote();
    }

    public Quote getRandomQuote() {

        return quotesRepository.findRandomQoute();

    }

    public User getUserByPrincipal(Principal principal) {

        if(principal == null){
            return null;
        }else{
            return userRepository.findByEmail(principal.getName());
        }

    }

    public List<Quote> getAllQuote() {
        return quotesRepository.findAll();
    }
}







