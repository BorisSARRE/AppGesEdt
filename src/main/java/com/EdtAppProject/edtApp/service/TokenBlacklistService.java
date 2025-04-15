package com.EdtAppProject.edtApp.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class TokenBlacklistService {

    // Utiliser une structure de données concurrente pour la thread-safety
    private final ConcurrentHashMap<String, Date> blacklistedTokens = new ConcurrentHashMap<>();

    // Planificateur pour nettoyer les tokens expirés
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @PostConstruct
    public void init() {
        // Nettoyer les tokens expirés toutes les heures
        scheduler.scheduleAtFixedRate(this::cleanupExpiredTokens, 1, 1, TimeUnit.HOURS);
    }

    public void blacklistToken(String token, Date expirationDate) {
        blacklistedTokens.put(token, expirationDate);
    }

    public boolean isTokenBlacklisted(String token) {
        Date expirationDate = blacklistedTokens.get(token);
        if (expirationDate == null) {
            return false;
        }

        // Si le token est expiré, on peut le retirer de la liste
        if (expirationDate.before(new Date())) {
            blacklistedTokens.remove(token);
            return false;
        }

        return true;
    }

    private void cleanupExpiredTokens() {
        Date now = new Date();
        blacklistedTokens.entrySet().removeIf(entry -> entry.getValue().before(now));
    }

    @PreDestroy
    public void shutdown() {
        scheduler.shutdown();
    }
}
