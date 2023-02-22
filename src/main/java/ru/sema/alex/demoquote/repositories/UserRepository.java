package ru.sema.alex.demoquote.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import ru.sema.alex.demoquote.models.User;

import javax.persistence.Id;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

}
