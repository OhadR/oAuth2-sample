package com.ohadr.oauth2.oauth2_client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.security.config.Customizer.withDefaults;

//@EnableWebMvc
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class SecurityConfig {

/*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                );

        return http.build();
    }
*/
    // @formatter:off
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
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .oauth2Login(withDefaults())        // to log users in using OAuth 2.0 or OpenID Connect 1.0
                .oauth2Client(withDefaults())       //  to use RestClient to obtain an access token for users in order to access a third-party API
                .logout(logout -> logout
                        .logoutSuccessUrl("/").permitAll())
        ;
        return http.build();
    }
    // @formatter:on

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