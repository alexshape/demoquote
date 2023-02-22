package ru.sema.alex.demoquote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication()
public class DemoquoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoquoteApplication.class, args);
    }

}
