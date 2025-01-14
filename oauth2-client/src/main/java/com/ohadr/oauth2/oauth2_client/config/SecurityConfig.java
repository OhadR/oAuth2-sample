package com.ohadr.oauth2.oauth2_client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   ClientRegistrationRepository clientRegistrationRepository) throws Exception {
        http
                .csrf(c -> c
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .csrfTokenRequestHandler(new SpaCsrfTokenRequestHandler())

                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/index.html", "/error", "/webjars/**", "/jwks", "/logout").permitAll()
                        .anyRequest().authenticated()
                )
//                .exceptionHandling(e -> e
//                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//                )
                // to log users in using OAuth 2.0 or OpenID Connect 1.0:
//                .oauth2Login(withDefaults())
                .oauth2Login(oauth2Login ->
                        oauth2Login.loginPage("/oauth2/authorization/ohads"))

//                .oauth2Client(withDefaults())       //  to use RestClient to obtain an access token for users in order to access a third-party API
                .logout(logout -> logout
                        .logoutSuccessUrl("/").permitAll())
        ;
        return http.build();
    }

//    private LogoutSuccessHandler oidcLogoutSuccessHandler(
//            ClientRegistrationRepository clientRegistrationRepository) {
//        OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler =
//                new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
//
//        // Set the location that the End-User's User Agent will be redirected to
//        // after the logout has been performed at the Provider
//        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}/logged-out");
//
//        return oidcLogoutSuccessHandler;
//    }

}