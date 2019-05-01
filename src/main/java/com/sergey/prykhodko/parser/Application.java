package com.sergey.prykhodko.parser;

import com.sergey.prykhodko.parser.managment.SaveManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private SaveManager saveManager;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        if (args.length != 1) {
            log.warn("There no or too many arguments!");
            return;
        }
        String filePath = args[0];
        saveManager.save(filePath);
    }
}
