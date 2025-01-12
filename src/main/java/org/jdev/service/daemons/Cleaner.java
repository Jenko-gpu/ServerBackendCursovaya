package org.jdev.service.daemons;

import org.jdev.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Cleaner {

    private final AuthService authService;

    @Autowired
    public Cleaner(AuthService authService) {
        this.authService = authService;
    }

    @Scheduled(fixedRate = 60000) // Каждую минуту
    public void cleanupOldTokens() {
        authService.clearOldTokens();
    }
}