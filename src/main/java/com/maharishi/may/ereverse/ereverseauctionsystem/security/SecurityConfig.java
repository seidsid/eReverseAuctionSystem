package com.maharishi.may.ereverse.ereverseauctionsystem.security;

import com.maharishi.may.ereverse.ereverseauctionsystem.security.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private final JWTAuthenticationProvider jwtAuthenticationProvider;
    private final AuthenticationFilter authenticationFilter;

    @Autowired
    public SecurityConfig(JWTAuthenticationProvider jwtAuthenticationProvider, AuthenticationFilter authenticationFilter)
    {
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.authenticationFilter=authenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .cors(httpSecurityCorsConfigurer -> {
                    httpSecurityCorsConfigurer.disable();
                })
                .addFilterAt(authenticationFilter, BasicAuthenticationFilter.class)
                .csrf()
                .disable()
                .httpBasic()
                .disable()
                .authorizeRequests()
                .mvcMatchers("/buyer","/supplier","/authenticate").permitAll()
                .mvcMatchers("/admin/activate").hasAuthority("admin")
                .mvcMatchers("/supplier/**").hasAuthority("supplier")
                .mvcMatchers("/buyer/**").hasAuthority("buyer")
                .anyRequest().authenticated();
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception
    {
        return super.authenticationManager();
    }
}
