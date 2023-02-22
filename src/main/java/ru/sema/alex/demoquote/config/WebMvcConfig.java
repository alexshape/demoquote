package ru.sema.alex.demoquote.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import ru.sema.alex.demoquote.compoments.PutUserInModelInterceptor;

import java.time.Duration;


public class WebMvcConfig  {

}
