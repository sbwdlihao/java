package com.lihao.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.annotation.PostConstruct;

/**
 * Created by sbwdlihao on 5/26/16.
 */
@SpringBootApplication
public class Application {


    private String url;

    public String getUrl() {
        return url;
    }

    @Value("${spring.datasource.url}")
    public void setUrl(String url) {
        this.url = url;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // swagger在SpringfoxWebMvcConfiguration中增加了一个PropertySourcesPlaceholderConfigurer
    // 并properties.setPlaceholderPrefix("$SPRINGFOX{");
    // properties.setIgnoreUnresolvablePlaceholders(true);
    // 由于增加了这个configurer，所以spring默认的configure就不起作用了，导致placeholder的解析都出现了问题
    // 所以再增加一个默认的configure
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
