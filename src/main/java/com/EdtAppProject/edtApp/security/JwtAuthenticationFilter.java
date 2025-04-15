package com.EdtAppProject.edtApp.security;

import com.EdtAppProject.edtApp.service.JwtService;
import com.EdtAppProject.edtApp.service.TokenBlacklistService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenBlacklistService tokenBlacklistService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // Vérifier si l'en-tête Authorization existe et a le format correct
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // Extraire le token JWT (ignorer le préfixe "Bearer ")
            jwt = authHeader.substring(7);

            // Vérifier si le token est dans la liste noire
            if (tokenBlacklistService.isTokenBlacklisted(jwt)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token invalide ou revoque");
                return;
            }

            // Extraire l'email/username du token
            userEmail = jwtService.extractUsername(jwt);

            // Extraire les rôles du token
            List<String> roles = jwtService.extractRoles(jwt);

            // Traiter l'authentification seulement si nous avons un username et qu'aucune authentification n'existe déjà
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                // Valider le token
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    // Créer un token d'authentification avec les autorités (rôles) extraites du JWT
                    // Cela garantit que les rôles dans le token sont utilisés pour l'autorisation
                    List<SimpleGrantedAuthority> authorities = roles.stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            authorities  // Utiliser les rôles extraits du JWT
                    );

                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    // Définir l'authentification dans le contexte
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    logger.debug("Authentification réussie pour l'utilisateur: {} avec rôles: {}",
                            userEmail, roles);
                } else {
                    logger.debug("Token JWT invalide pour l'utilisateur: {}", userEmail);
                }
            }
        } catch (Exception e) {
            // Logger l'exception mais ne pas la propager - continuer sans authentification
            logger.error("Échec d'authentification JWT: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}