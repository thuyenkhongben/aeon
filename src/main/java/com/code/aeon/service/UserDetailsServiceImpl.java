package com.code.aeon.service;

import com.code.aeon.model.User;
import com.code.aeon.repository.UserRepository;
import com.code.aeon.security.jwt.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found With -> username or email : " + username)
        );
        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails findById ( Long id ) {
        User user =userRepository.findById(id).orElseThrow(
        () -> new UsernameNotFoundException("User Not found With id :" + id));
        return UserPrincipal.create(user);
    }

}
