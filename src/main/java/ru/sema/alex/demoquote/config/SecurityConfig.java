package ru.sema.alex.demoquote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import ru.sema.alex.demoquote.compoments.PutUserInModelInterceptor;
import ru.sema.alex.demoquote.servises.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }


    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PutUserInModelInterceptor());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeRequests().antMatchers("/*").permitAll();
        http.authorizeRequests().antMatchers("/users/profile").authenticated();
        http.authorizeRequests().antMatchers("/users/new").anonymous();
        http.authorizeRequests().antMatchers("/users/login").anonymous();
        http.authorizeRequests().antMatchers("/quote/new").authenticated();
        http.formLogin().loginPage("/users/login").defaultSuccessUrl("/users/profile");
        http.logout().logoutUrl("/users/logout").permitAll().logoutSuccessUrl("/");

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService(); // (1)
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
