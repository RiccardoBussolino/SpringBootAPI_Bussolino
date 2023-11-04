package com.fabrick.bussolino;

import com.fabrick.bussolino.controller.ApiController;
import com.fabrick.bussolino.repository.TransazioneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan("com.fabrick.bussolino")
public class ApplicationMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationMain.class);

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
        LOGGER.info("- SERVER AVVIATO -");
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
