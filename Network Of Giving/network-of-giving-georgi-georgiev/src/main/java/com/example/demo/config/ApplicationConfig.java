package com.example.demo.config;


import com.example.demo.models.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class ApplicationConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthProvider authProvider;
    @Autowired
    private AuthenticationEventPublisher authEventPublisher;
    @Autowired
    private UserService userService;
    @Autowired
    private LogoutHandler logoutHandler;
    private static final String[] PUBLIC_APIS =
            new String[]{"/", "/user/login", "/user/logout", "/user/registration","/home/charity/*","user/donate/charity/*",
                    "/charities/*","/user/list","/donations",
            "/charities"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(PUBLIC_APIS)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .deleteCookies(SecurityConstants.ACCESS_TOKEN, "JSESSIONID")
                .invalidateHttpSession(true)
                .logoutSuccessHandler(logoutHandler)
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(
                        new AuthenticationEntryPoint() {
                            @Override
                            public void commence(
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    AuthenticationException authException)
                                    throws IOException, ServletException {
                                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                            }
                        });
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
        auth.authenticationEventPublisher(authEventPublisher);
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
