package ru.sema.alex.demoquote.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ru.sema.alex.demoquote.models.User;
import ru.sema.alex.demoquote.repositories.UserRepository;
import ru.sema.alex.demoquote.servises.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

public class PutUserInModelInterceptor implements HandlerInterceptor {

    private UserRepository userRepository;
    public PutUserInModelInterceptor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest aRequest, HttpServletResponse aResponse, Object aHandler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest aRequest, HttpServletResponse aResponse, Object aHandler, ModelAndView aModelAndView) throws Exception {

        if(aModelAndView != null) {
            Principal principal = aRequest.getUserPrincipal();

            if(principal != null){

                User user = userRepository.findByEmail(principal.getName());
                if(user != null){
                    aModelAndView.addObject("__user", user);
                    aModelAndView.addObject("__userMainImageId", user.getImageOwner().getMainImageId());
            }}
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest aRequest, HttpServletResponse aResponse, Object aHandler, Exception aEx) throws Exception { }

}