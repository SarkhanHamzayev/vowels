package com.luxsoft.vowels;


import com.luxsoft.vowels.config.PropertiesConfiguration;
import com.luxsoft.vowels.prosessor.VowelsExecutor;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@AllArgsConstructor
@SpringBootApplication
public class Application implements CommandLineRunner {

    private VowelsExecutor vowelsExecutor;
    private PropertiesConfiguration configuration;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws IOException {
        vowelsExecutor.process(configuration);
    }
}
