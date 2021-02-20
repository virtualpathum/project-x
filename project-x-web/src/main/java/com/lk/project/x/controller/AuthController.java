package com.lk.project.x.controller;/* Created by Dell on 30/12/2019 */

import com.lk.project.x.config.JwtTokenProvider;
import com.lk.project.x.entity.RoleEntity;
import com.lk.project.x.entity.RoleName;
import com.lk.project.x.entity.UserEntity;
import com.lk.project.x.events.OnRegistrationCompleteEvent;
import com.lk.project.x.exception.AppException;
import com.lk.project.x.payload.ApiResponse;
import com.lk.project.x.payload.JwtAuthenticationResponse;
import com.lk.project.x.payload.LoginRequest;
import com.lk.project.x.payload.SignUpRequest;
import com.lk.project.x.repo.RoleRepository;
import com.lk.project.x.repo.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Inject
    AuthenticationManager authenticationManager;

    @Inject
    UserRepository userRepository;

    @Inject
    RoleRepository roleRepository;

    @Inject
    PasswordEncoder passwordEncoder;

    @Inject
    JwtTokenProvider tokenProvider;

    @Inject
    ApplicationEventPublisher eventPublisher;

    //@Inject
    //private HttpServletRequest request;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUserName(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.OK);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.OK);
        }
        // Creating user's account
        UserEntity user = new UserEntity();
        user.setFirstName(signUpRequest.getName());
        user.setUserName(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        RoleEntity userRole = roleRepository.findByName("ROLE_USER");

        user.setRoles(Collections.singleton(userRole));

        UserEntity registeredUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/user/"+user.getId())
                .buildAndExpand(registeredUser.getUserName()).toUri();

        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest();


        String appUrl = request.getContextPath();

        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registeredUser,
                request.getLocale(), appUrl));

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}
