package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
/// since Field injection is not recommended
//    @Autowired
//    DataSource dataSource;
/// we go for this
/// // Constructor injection
//private final DataSource dataSource;
//
//
//    public SecurityConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
/// but im using lombok so final result is
private final DataSource dataSource; //and i add @RequiredArgsConstructor   (EmbarkX)

    ///  Telusko
    private final UserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;



    /// combined
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> {})   // enable CORS, use config in WebMvcConfigurer
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/project",
                                "/skills",
                                "/certification",
//                                "/login",
                                "/users/register",
//                                "/signin"
                                "/users/signin"
                        ).permitAll()  // allow GET from  Angular
                        .anyRequest().authenticated())
                        .httpBasic(Customizer.withDefaults())
                //statless !
                        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
       // .build();
        ;
            

        return http.build();
    }

    ///EmbarkX
//    @Bean
//    UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();
//        UserDetails user2 = User.withUsername("user").password(passwordEncoder().encode("user")).roles("USER").build();
//
//
//        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//        //return new InMemoryUserDetailsManager(user, user2);
//        userDetailsManager.createUser(user);
//        userDetailsManager.createUser(user2);
//        return userDetailsManager;
//    }
//
//    /// JdbcUserDetailsManager implements UserDetailsManager and UserDetailsManager extends UserDetailsService
//    //in short (visually better) : JdbcUserDetailsManager --> UserDetailsManager --> UserDetailsService
//
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    /// Telusko
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService (userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
