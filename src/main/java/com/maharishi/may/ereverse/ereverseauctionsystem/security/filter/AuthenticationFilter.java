package com.maharishi.may.ereverse.ereverseauctionsystem.security.filter;

import com.maharishi.may.ereverse.ereverseauctionsystem.security.JWTAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private  AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        logger.info("authenticating request at "+request.getRequestURL());
        String authorizationHeader=request.getHeader("Authorization");
        if(authorizationHeader!=null&&authorizationHeader.startsWith("Bearer "))
        {
            logger.trace("request with jwt token");
            try
            {
                Authentication authentication=authenticationManager.authenticate(new JWTAuthentication(authorizationHeader.substring(7)));
                if(authentication.isAuthenticated())
                {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(request,response);
                }
                else
                {
                    logger.warn("invalid authentication attempt");
                    throw new BadCredentialsException("invalid jwt token");
                }
            }
            catch (AuthenticationException e)
            {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        else
        {
            filterChain.doFilter(request,response);
        }
    }
}
