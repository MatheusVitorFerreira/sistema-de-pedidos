package com.mtdev00.Sistema_Cadastro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SistemaCadastroApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SistemaCadastroApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       
    }

}	