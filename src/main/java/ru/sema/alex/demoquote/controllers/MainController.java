package ru.sema.alex.demoquote.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sema.alex.demoquote.models.Quote;
import ru.sema.alex.demoquote.servises.QuoteService;

@Controller
public class MainController {

    @Autowired
    private QuoteService quoteService;

    public MainController(QuoteService quoteService) {
    }

    @GetMapping("/")
    public String mainPage(Model model){

        return "redirect:/quote/rate/top10";
    }

}
