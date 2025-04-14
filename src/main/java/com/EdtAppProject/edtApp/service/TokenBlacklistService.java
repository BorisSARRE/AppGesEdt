package com.EdtAppProject.edtApp.service;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBlacklistService {


    // Stockage en mémoire des tokens invalidés avec leur date d'expiration
    private final Map<String, Date> blacklistedTokens = new ConcurrentHashMap<>();

    /**
     * Ajoute un token à la liste noire jusqu'à sa date d'expiration
     * @param token Le token JWT à invalider
     * @param expiryDate La date d'expiration du token
     */
    public void blacklistToken(String token, Date expiryDate) {
        blacklistedTokens.put(token, expiryDate);
    }

    /**
     * Vérifie si un token est dans la liste noire
     * @param token Le token JWT à vérifier
     * @return true si le token est invalidé, false sinon
     */
    public boolean isTokenBlacklisted(String token) {
        if (!blacklistedTokens.containsKey(token)) {
            return false;
        }

        Date expiryDate = blacklistedTokens.get(token);
        if (expiryDate.before(new Date())) {
            // Le token est expiré, on peut le retirer de la liste noire
            blacklistedTokens.remove(token);
            return false;
        }

        return true;
    }

    /**
     * Nettoie les tokens expirés de la liste noire
     * Cette méthode pourrait être appelée périodiquement via un @Scheduled
     */
    public void cleanupExpiredTokens() {
        Date now = new Date();
        blacklistedTokens.entrySet().removeIf(entry -> entry.getValue().before(now));
    }
}
