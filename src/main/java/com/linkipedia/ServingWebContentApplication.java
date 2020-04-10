package com.linkipedia;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServingWebContentApplication {
    private static Graph g;
    public static void main(String[] args) {
        SpringApplication.run(ServingWebContentApplication.class, args);
    }

    @PostConstruct
    private static void buildGraph(){
        g = API.createGraph();
    }
    public static Graph getGraph(){
        return g;
    }
}
