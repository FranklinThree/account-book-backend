package com.github.franklinthree.main.config;


import com.github.franklinthree.main.model.server.PictureFactory;
import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.github.franklinthree.main.model"})
public class SpringConfig {

    @Bean(name = "pictureFactory")
    public PictureFactory pictureFactory() {
        return new PictureFactory();
    }

    @Bean(name = "defGson")
    public Gson gson() {
        return new Gson();
    }
}
