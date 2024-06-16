package com.ecommerce.controllers;

import com.ecommerce.config.JwtProvider;
import com.ecommerce.models.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.response.AuthResponse;
import com.ecommerce.response.LoginRequest;
import com.ecommerce.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {

        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();
        User isExistEmail = userRepository.findByEmail(email);
        if (isExistEmail != null) {
            throw new Exception("Email is Already Taken");
        } else {
            User createdUser = new User();
            createdUser.setEmail(email);
            createdUser.setPassword(passwordEncoder.encode(password));
            createdUser.setFullName(fullName);
            User savedUser = userRepository.save(createdUser);
            Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtProvider.generateToken(authentication);

            AuthResponse res = new AuthResponse();
            res.setJwt(token);
            res.setMessage("Signup Successfull");
            return res;
        }
    }


    @PostMapping("/sign-in")
    public AuthResponse loginHandler(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        System.out.println(username+ password);
        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse();
        res.setJwt(token);
        res.setMessage("Signin Successfull");
        return res;
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("User Not Found");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Password Not Matching");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
