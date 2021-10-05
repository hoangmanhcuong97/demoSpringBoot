package com.example.demospringboot1.service.userservice;

import com.example.demospringboot1.model.AppUser;
import com.example.demospringboot1.repository.IAppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    private IAppUserRepo userRepo;

    @Override
    public AppUser getUserByUsername(String name) {
        return userRepo.findByUserName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = getUserByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(user.getRole());

        UserDetails userDetails = new User(
                user.getUserName(),
                user.getPassword(),
                authorities
        );
        return userDetails;
    }
}
