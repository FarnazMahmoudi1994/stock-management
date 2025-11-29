package com.example.stock_manager.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ApplicationLifecycle {

    @PostConstruct
    private void onStart() {

    }
}
