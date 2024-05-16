package edu.mtdev00.sistemapedido;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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