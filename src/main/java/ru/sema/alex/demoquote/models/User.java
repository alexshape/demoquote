package ru.sema.alex.demoquote.models;

import com.sun.istack.Nullable;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.sema.alex.demoquote.models.imagemodels.Image;
import ru.sema.alex.demoquote.models.imagemodels.ImageOwner;
import ru.sema.alex.demoquote.models.imagemodels.ImageOwnerSingle;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    public Long getId() {
        return id;
    }

    public String getIdString() {
        return id.toString();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    public List<Quote> getQuotes() {
        return quotes;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private List<Quote> quotes;

    private String name;

    @Column(name = "password", length = 1000)
    private String password;

    public String getCreationDateFormat() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return creationDate.format(formatter);
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    private LocalDateTime creationDate;

    public ImageOwner getImageOwner() {
        return imageOwner;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="imageOwner")
    @Nullable
    private ImageOwner imageOwner = new ImageOwnerSingle();

    @PrePersist
    public void init(){
        creationDate = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public void encodePassword(){

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        this.password = encoder.encode(this.password);

    }
}

