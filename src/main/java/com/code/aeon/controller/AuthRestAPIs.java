package com.code.aeon.controller;

import com.code.aeon.model.Role;
import com.code.aeon.model.RoleName;
import com.code.aeon.model.User;
import com.code.aeon.playLoad.request.LoginForm;
import com.code.aeon.playLoad.request.SignUpForm;
import com.code.aeon.playLoad.respone.JwtResponse;
import com.code.aeon.repository.RoleRepository;
import com.code.aeon.repository.UserRepository;
import com.code.aeon.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUSer(@Valid @RequestBody  LoginForm loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder .getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUSer(@Valid @RequestBody SignUpForm signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())){
            return  new ResponseEntity<String>("Fail-> Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())){
            return new ResponseEntity<String>("Fail -> Email is already in user !" , HttpStatus.BAD_REQUEST);
        }

        // create username

        User user = new User(signUpRequest.getName() , signUpRequest.getUsername(),
                signUpRequest.getPassword(), signUpRequest.getEmail() , encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();

        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role ->{
            switch (role){
                case "admin" :
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow(
                            () -> new RuntimeException("Fail -> Cause: User Role not find.")
                    );
                    roles.add(adminRole);
                    break;
                case "pm":
                    Role pmRole = roleRepository.findByName(RoleName.ROLE_PM).orElseThrow(
                            () -> new RuntimeException("Fail -> Cause: User Role not find.")
                    );
                    roles.add(pmRole);
                    break;

                case "user":
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(
                            () -> new RuntimeException("Fail -> Cause: User Role not find.")
                    );
                    roles.add(userRole);
                    break;
            }
        });

        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok().body("User registered successfully");


    }
}
