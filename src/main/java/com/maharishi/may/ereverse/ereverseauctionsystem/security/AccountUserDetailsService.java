package com.maharishi.may.ereverse.ereverseauctionsystem.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AccountUserDetailsService implements UserDetailsService
{
    //private final UserRepository userRepository;

//    @Autowired
//    public AccountUserDetailsService(UserRepository userRepository)
//    {
//        this.userRepository = userRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        throw new RuntimeException();
//        User user=userRepository.findByUsername(username);
//        if(user!=null)
//        {
//            org.springframework.security.core.userdetails.User.UserBuilder builder=org.springframework.security.core.userdetails.User.withUsername(username);
//            return builder.password(user.getPassword()).authorities("user").accountLocked(user.isFrozen()).build();
//        }
//        else throw new UsernameNotFoundException("username not found for username "+username);
    }
}
