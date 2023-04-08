package com.github.franklinthree.main.config;


import com.github.franklinthree.main.pojo.PictureFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.github.franklinthree.main.pojo"})
public class SpringConfig {

    @Bean(name = "pictureFactory")
    public PictureFactory pictureFactory() {
        return new PictureFactory();
    }
}
