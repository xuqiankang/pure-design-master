package com.qingge.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(SpringbootApplication.class, args);
            System.out.println("job start success !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
