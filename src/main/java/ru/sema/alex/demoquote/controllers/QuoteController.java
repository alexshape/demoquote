package ru.sema.alex.demoquote.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.sema.alex.demoquote.models.Quote;
import ru.sema.alex.demoquote.models.User;
import ru.sema.alex.demoquote.servises.QuoteService;
import ru.sema.alex.demoquote.servises.ScoreService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/quote")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @Autowired
    ScoreService scoreService;

    public QuoteController(QuoteService quoteService) {
    }

    @GetMapping("/{id}")
    public String quoteInfo(@PathVariable Long id, Model model){

        Quote quote = quoteService.getQuote(id);

        if(quote != null){
            model.addAttribute("quote", quote);
            model.addAttribute("author", quote.getAuthor());
            model.addAttribute("hasMainImgage", quote.getImageOwner().hasMainImgage());
            model.addAttribute("MainImageId", quote.getImageOwner().getMainImageId());

            model.addAttribute("images", quote.getImageOwner().getImages());

            return "quotes/quote";
        }else{
            model.addAttribute("message", "We haven't find the quote");
            return "quotes/blankquote";
        }
    }

    @GetMapping("/last")
    public String lastQuote(Principal principal, Model model){

        Quote quote = quoteService.getLastQuote();

        if(quote == null){
            model.addAttribute("message", "Sorry, we do not have quotes yet(((");

            User user = quoteService.getUserByPrincipal(principal);
            if(user != null)
                model.addAttribute("user", user);

            return "quotes/blankquote";
        }else{
            return "redirect:/quote/"+quote.getId();
        }

    }

    @GetMapping("/new")
    public String newQuote(){

        return "quotes/newquote";
    }

    @GetMapping("/rate/{top}")
    public String top10(Model model, @PathVariable String top){

        Quote randomQoute = quoteService.getRandomQuote();

        model.addAttribute("randomQoute", randomQoute);
        model.addAttribute("showRandomQuote", randomQoute != null);

        if(quoteService.getAllQuote().size() > 0)
            model.addAttribute("allQuotes", quoteService.getAllQuote());

        return "quotes/top10flop10quotes";
    }


    @PostMapping("/{id}/{typeReaction}")
    public String reaction(HttpServletRequest request, @PathVariable Long id, @PathVariable String typeReaction, Model model){

        Quote quote = quoteService.getQuote(id);

        if (quote != null) {
            if("like".equals(typeReaction)){
                scoreService.likeQuote(quote);
            }else{
                scoreService.disLikeQuote(quote);
            }
        }

        String path = "";
        if(request.getHeader("referer").contains("top10")){
            path = "top10";
        }else{
            path = "flop10";
        }

        return "redirect:/quote/rate/"+path;

    }

    @PostMapping("/create")
    public String create(Principal principal, HttpServletRequest request, @RequestParam(name = "image1", required = false) MultipartFile firstPhoto,
                         @RequestParam(name = "image2", required = false)  MultipartFile secondPhoto){

        Quote quote = new Quote(request.getParameter("title"),
                request.getParameter("description"),
                request.getParameter("data"),
                quoteService.getUserByPrincipal(principal));

        quoteService.saveQuote(quote, firstPhoto, secondPhoto);


        return "redirect:/quote/"+quote.getId();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){

        quoteService.deleteQuote(id);
        return "redirect:/";
    }

}
