package ru.sema.alex.demoquote.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.sema.alex.demoquote.models.User;
import ru.sema.alex.demoquote.models.imagemodels.Image;
import ru.sema.alex.demoquote.repositories.ImageRepository;
import ru.sema.alex.demoquote.repositories.UserRepository;

public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepository imageRepository;

    public boolean createUser(User user){

        if(userRepository.findByEmail(user.getUsername())==null){
            userRepository.save(user);
            return true;
        }else{
            return false;
        }

    }

    public void saveUser(User user){

        userRepository.save(user);
    }

    public void saveUser(User user, MultipartFile photo){

        Image image = Image.getImageFromMultipartFile(photo);
        if(image != null){
            Image.saveImageToImageOwner(imageRepository, user.getImageOwner(), image);
            user.getImageOwner().setMainImage(image);
        }
        saveUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user= userRepository.findByEmail(s);
        if (user == null) {
            throw new UsernameNotFoundException("Unknown user: "+s);
        }

        org.springframework.security.core.userdetails.User.UserBuilder userBuilder = org.springframework.security.core.userdetails.User.builder();
        userBuilder.username(user.getUsername());
        userBuilder.password(user.getPassword());
        userBuilder.roles("USER");

        return  userBuilder.build();
    }

    public User findByEmail(String name) {
        return userRepository.findByEmail(name);
    }
}
