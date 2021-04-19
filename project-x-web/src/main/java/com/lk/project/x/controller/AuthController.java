package com.lk.project.x.controller;/* Created by Dell on 30/12/2019 */

import com.lk.project.x.authentication.UserPrincipal;
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
import com.lk.project.x.resource.UserResource;
import com.lk.project.x.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Inject
    AuthenticationManager authenticationManager;

    @Inject
    ModelMapper mapper;

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

    @Inject
    UserService userService;

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
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt,userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
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

        Set<String> roleList = signUpRequest.getRoles();
        Set<RoleEntity> roles = new HashSet<>();
        if (roleList == null) {
            RoleEntity userRole = roleRepository.findByName(RoleName.ROLE_USER.name())
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            roleList.forEach(role -> {
                switch (role) {
                    case "admin":
                        RoleEntity adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN.name())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        RoleEntity modRole = roleRepository.findByName(RoleName.ROLE_MODERATOR.name())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        RoleEntity userRole = roleRepository.findByName(RoleName.ROLE_USER.name())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        UserEntity user =  mapper.map(signUpRequest,UserEntity.class);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        //RoleEntity userRole = roleRepository.findByName("ROLE_USER");

        user.setRoles(roles);

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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/user/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<UserResource> listAll() {
        //logger.info("Fetching Users");
        return userService.findAll();
    }
}
