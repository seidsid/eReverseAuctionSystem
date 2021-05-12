package com.maharishi.may.ereverse.ereverseauctionsystem.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JWTAuthenticationProvider implements AuthenticationProvider
{
    private AccountUserDetailsService accountUserDetailsService;
    private Logger logger= LoggerFactory.getLogger(JWTAuthenticationProvider.class);

    @Value("${auction.security.jwt.key}")
    private String key;

    @Autowired
    public JWTAuthenticationProvider(AccountUserDetailsService accountUserDetailsService)
    {
        this.accountUserDetailsService = accountUserDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String jwtToken=authentication.getCredentials().toString();
        try
        {
            logger.info("validating jwt token "+jwtToken);
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key.getBytes())
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();
            UserDetails userDetails;
            if(claims.get("role").toString().equalsIgnoreCase("user"))
            {
                userDetails=accountUserDetailsService.loadUserByUsername(claims.get("username").toString());
                if(!userDetails.isAccountNonLocked())throw new LockedException("account is locked for "+userDetails.getUsername());
                return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(), List.of(new SimpleGrantedAuthority("user")));
            }
            else if(claims.get("role").toString().equalsIgnoreCase("pay"))
            {
                return new UsernamePasswordAuthenticationToken("payment","pass",List.of(new SimpleGrantedAuthority("pay")));
            }
            else
            {
                throw new IllegalStateException();
            }
        }
        catch (JwtException j)
        {
            logger.warn("invalid jwt toke attempt. jwt:"+jwtToken);
            throw new BadCredentialsException("invalid jwtToken ");
        }
    }

    @Override
    public boolean supports(Class<?> authentication)
    {
        return authentication.isAssignableFrom(JWTAuthentication.class);
    }
}
