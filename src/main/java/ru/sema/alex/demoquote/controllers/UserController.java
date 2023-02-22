package ru.sema.alex.demoquote.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.sema.alex.demoquote.models.User;
import ru.sema.alex.demoquote.repositories.UserRepository;
import ru.sema.alex.demoquote.servises.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/new")
    public String newUser(Model model){
        return "users/newuser";
    }

    @GetMapping("/successfulreg")
    public String successfulreg(Model model){
        return "users/successfulregistration";
    }

    @GetMapping("/login")
    public String login(){
        return "users/loginpage";
    }

    @GetMapping("/info/{id}")
    public String login(Principal principal, @PathVariable(required = true, name = "id") Long id, Model model){

        User user = userRepository.findById(id).orElse(null);

        if(principal != null && user.getUsername().equals(principal.getName())){
            return "redirect:/users/profile";
        }

        if(user != null) {
            model.addAttribute("user", user);
            model.addAttribute("hasMainImgage", user.getImageOwner().hasMainImgage());
            model.addAttribute("MainImageId", user.getImageOwner().getMainImageId());
            model.addAttribute("quotes", user.getQuotes());
        }

        return "users/userinfo";
    }

    @PostMapping("/logout")
    public String logout(){
        return "/";
    }

    @GetMapping("/edit")
    public RedirectView edit(RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute("editProfile", true);
        return new RedirectView("/users/profile");
    }

    @PostMapping("/edit")
    public RedirectView editUser(Principal principal,
                                 HttpServletRequest request,
                                 @RequestParam(name = "photo", required = false) MultipartFile photo){

        User user = userRepository.findByEmail(principal.getName());

        user.setName(request.getParameter("name"));

        ((UserService)userDetailsService).saveUser(user, photo);

        return new RedirectView("/users/profile");
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model){

        User user = userRepository.findByEmail(principal.getName());

        model.addAttribute("user", user);
        model.addAttribute("hasMainImgage", user.getImageOwner().hasMainImgage());
        model.addAttribute("MainImageId", user.getImageOwner().getMainImageId());

        if(user.getQuotes().size() > 0)
            model.addAttribute("quotes", user.getQuotes());

        return "users/profile";
    }

    @PostMapping("/login")
    public RedirectView loginEnter(HttpServletRequest request, RedirectAttributes attributes, @RequestParam(name = "username", required = false) String username,
                                   @RequestParam(name = "password", required = false) String password){


        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return new RedirectView("/users/profile/");
        }catch (AuthenticationException ex){
            setMistake(attributes, "We can not allow to enter");
            return new RedirectView(request.getHeader("referer"));
        }catch (Exception ex){
            return new RedirectView(request.getHeader("referer"));
        }

    }

    @PostMapping("/create")
    public RedirectView createNewUser(HttpServletRequest request, RedirectAttributes model, @RequestParam(name = "username", required = false) String username,
                                @RequestParam(name = "name", required = false) String name,
                                @RequestParam(name = "password", required = false) String password,
                                @RequestParam(name = "password-repeat", required = false) String passwordRepeat){

        User newUser = new User();
        newUser.setEmail(username);
        newUser.setName(name);
        newUser.setPassword(password);

        if(!validateNewUser(newUser, model,passwordRepeat)) {
            return new RedirectView("/users/new");
        }else{

            newUser.encodePassword();

            org.springframework.security.core.userdetails.User.UserBuilder userBuilder = org.springframework.security.core.userdetails.User.builder();
            userBuilder.username(newUser.getUsername());
            userBuilder.password(newUser.getPassword());
            userBuilder.roles("USER");
            UserDetails user = userBuilder.build();

            userRepository.save(newUser);

        }

        model.addFlashAttribute("name", name);

        return new RedirectView("/users/successfulreg/");
    }

    private void setMistake(Model model, String message){

        if(model instanceof RedirectAttributes){
            RedirectAttributes redirectAttributes = (RedirectAttributes)model;
            redirectAttributes.addFlashAttribute(getNameAttributehaveMistake(), true);
            redirectAttributes.addFlashAttribute(getNameAttributemessage(), message);
        }else{
            model.addAttribute(getNameAttributehaveMistake(), true);
            model.addAttribute(getNameAttributemessage(), message);
        }

    }

    public String getNameAttributehaveMistake(){
        return "haveAuthMistake";
    }

    public String getNameAttributemessage(){
        return "messageAuth";
    }

    private boolean validateNewUser(User user, Model model){

        if(model instanceof RedirectAttributes)
            ((RedirectAttributes)model).addAttribute("user", user);
           else
               model.addAttribute("user", user);

        User userFromDb = userRepository.findByEmail(user.getEmail());
        if(userFromDb != null){

            setMistake(model, "Unfortunately such a user already exists");

            return false;
        }

        return true;
    }

    private boolean validateNewUser(User user, Model model, String passwordRepeat){

        if(model instanceof RedirectAttributes)
            ((RedirectAttributes)model).addFlashAttribute("user", user);
        else
            model.addAttribute("user", user);

        if(!user.getPassword().equals(passwordRepeat)){
            //to do whith JS
            setMistake(model, "Passwords are different");
            return false;
        }

        return validateNewUser(user, model);

    }

}
