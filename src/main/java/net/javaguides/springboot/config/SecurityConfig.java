package net.javaguides.springboot.config;

import net.javaguides.springboot.security.JwtAuthenticationEntryPoint;
import net.javaguides.springboot.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//to enable method level security so only ADMIN can access POST,PUT & DELETE endpoint
@EnableMethodSecurity
public class SecurityConfig {


    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    private JwtAuthenticationFilter authenticationFilter;

    public SecurityConfig(JwtAuthenticationEntryPoint authenticationEntryPoint,
                          JwtAuthenticationFilter authenticationFilter) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                //.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll()// Allow GET requests to api/**
                                .requestMatchers("/api/auth/**").permitAll()
                                .anyRequest().authenticated()
                )  // Authenticate all other requests
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint)
                ).sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails sumit = User.builder()
//                .username("sumit")
//                //.password("sumit25")   as it was a plain text
//                .password(passwordEncoder().encode("sumit25"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                //.password("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//        System.out.println("Encoded password for admin: " + passwordEncoder().encode("admin"));
//        return new InMemoryUserDetailsManager(sumit, admin);
//    }

}