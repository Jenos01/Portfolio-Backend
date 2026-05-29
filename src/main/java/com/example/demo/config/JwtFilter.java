package com.example.demo.config;

import com.example.demo.Service.JWTService;

import com.example.demo.Service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/// Telusko
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    //*
    private final ApplicationContext context; //to avoid cyclic redundancy (teb3a l declaration mta3 UserDetails louta)

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        if (request.getServletPath().startsWith("/auth")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        System.out.println("JWT FILTER HIT: " + request.getServletPath());
        //System.out.println("Auth header: " + authHeader);

            String authHeader = request.getHeader("Authorization");
            //***************
        /// Temporaire
       // String authHeader = request.getHeader("Authorization");
        System.out.println("=== JWT FILTER ===");
        System.out.println("Path: " + request.getServletPath());
        System.out.println("Auth Header: " + authHeader);
       // System.out.println("Username extracted: " + username);
        System.out.println("SecurityContext auth: " + SecurityContextHolder.getContext().getAuthentication());




            //****************
        String token = null;
        String username = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUserName(token);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            /// if the token is valid, in that case i will create the authentication object

            // UserDetails userDetails = context.getBean("userDetails", UserDetails.class); //will give me an empty object so we go to this solution below
            //*
            UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);

            if (jwtService.validateToken(token, userDetails)) {


                List<String> roles = jwtService.extractRoles(token);

                List<GrantedAuthority> authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

//            if(jwtService.validateToken(token, userDetails)){
//                UsernamePasswordAuthenticationToken authtoken =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authtoken);


                //***trying to found out why comment are not posted*****/
//                System.out.println("UserDetails username: " + userDetails.getUsername());
//                System.out.println("Token username: " + jwtService.extractUserName(token));
//                System.out.println("Is expired: " + jwtService.validateToken(token, userDetails));
                //******************************************************/
//            }
//        }
//        filterChain.doFilter(request, response);

                //*
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                authorities
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }else {
                System.out.println("TOKEN VALIDATION FAILED");
            }
        }
        filterChain.doFilter(request, response);


    }
}
