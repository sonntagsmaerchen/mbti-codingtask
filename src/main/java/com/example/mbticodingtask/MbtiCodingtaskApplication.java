package com.example.mbticodingtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class MbtiCodingtaskApplication {

    public static void main(final String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        SpringApplication.run(MbtiCodingtaskApplication.class, args);
    }

}
