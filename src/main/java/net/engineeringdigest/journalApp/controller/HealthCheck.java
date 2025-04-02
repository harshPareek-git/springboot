package net.engineeringdigest.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class HealthCheck {
    @GetMapping("health-check")
    public String health(){
        System.out.println("------");
        return "OK";
    }
}
