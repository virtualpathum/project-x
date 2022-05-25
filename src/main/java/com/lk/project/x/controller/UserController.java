
package com.lk.project.x.controller;

import com.lk.project.x.config.JwtTokenProvider;
import com.lk.project.x.entity.UserEntity;
import com.lk.project.x.entity.VerificationTokenEntity;
import com.lk.project.x.exception.UserNotFoundException;
import com.lk.project.x.repo.RoleRepository;
import com.lk.project.x.repo.UserRepository;
import com.lk.project.x.resource.UserResource;
import com.lk.project.x.service.UserService;
import com.lk.project.x.util.GenericResponse;
import com.lk.project.x.web.resource.finder.UserResourceFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class UserController {
    /** The Constant logger. */
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

   @Inject
    private UserResourceFinder resourceFinder;

    @Inject
    private UserService service;

    @Inject
    private MessageSource messageSource;

    @Inject
    private JavaMailSender mailSender;

    @Inject
    private Environment env;

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    UserRepository userRepository;

    @Inject
    RoleRepository roleRepository;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private JwtTokenProvider tokenProvider;

    @Inject
    private ApplicationEventPublisher eventPublisher;

    /**
     * Instantiates a new student controller.
     *
     * @param resourceFinder the resource finder
     * @param service the service
     */
    @Autowired
    public UserController(UserService service, JavaMailSender mailSender, Environment env, MessageSource messageSource, UserResourceFinder resourceFinder) {
        this.resourceFinder = resourceFinder;
        this.service = service;
        this.mailSender = mailSender;
        this.env = env;
        this.messageSource = messageSource;
    }

    /**
     * List all.
     *
     * @return the list
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/user/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<UserResource> listAll() {
        logger.info("Fetching Users");
        return resourceFinder.findAll();
    }



    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserResource get(@PathVariable("id") long id) {
        logger.info("Fetching User with id {}", id);
        return resourceFinder.findOne(id);

    }

   /* *//**
     * Creates.
     *
     * @return the student resource
     *//*
    //@CrossOrigin(origins = "*", allowedHeaders = "*")
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public UserResource create(@RequestBody UserResource resource) {
        logger.info("Creating User : {}", resource);
        return service.saveOrUpdate(resource);

    }*/

    /*@CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserResource resource) {
        if(userRepository.existsByUserName(resource.getUserName())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.OK);
        }

        if(userRepository.existsByEmail(resource.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.OK);
        }
        // Creating user's account
        UserEntity user = new UserEntity();
        user.setFirstName(resource.getFirstName());
        user.setLastName(resource.getLastName());
        user.setUserName(resource.getUserName());
        user.setEmail(resource.getEmail());
        user.setPassword(passwordEncoder.encode(resource.getPassword()));

        Optional<RoleEntity> userRole = roleRepository.findByName("ROLE_USER");

        user.setRoles(userRole);

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
    }*/

    /**
     * Update.
     *
     * @param id the id
     * @param resource the resource
     * @return the student resource
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public UserResource update(@PathVariable("id") long id, @RequestBody UserResource resource) {
        logger.info("Updating User with id {}", id);
        return service.saveOrUpdate(resource);
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting User with id {}", id);
        service.delete(id);
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/user/confirm", method = RequestMethod.POST)
    public String confirmRegistration(@RequestParam("token") String token) {
        System.out.println("///// inside confirmRegistration : " + token);

        HttpServletRequest request =
                ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                        .getRequest();

        Locale locale = request.getLocale();

        VerificationTokenEntity verificationToken = service.getVerificationToken(token);
        if (verificationToken == null) {
            System.out.println("//// messageSource : " + messageSource);
            String message = messageSource.getMessage("auth.message.invalidToken", null, locale);
            //model.addAttribute("message", message);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        UserEntity user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = messageSource.getMessage("auth.message.expired", null, locale);
            //model.addAttribute("message", messageValue);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        user.setEnabled(true);
        service.saveRegisteredUser(user);
        return "redirect:/login.html?lang=" + request.getLocale().getLanguage();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/user/changePassword", method = RequestMethod.GET)
    public String showChangePasswordPage(@RequestParam("token") String token) {
        String result = service.validatePasswordResetToken(token);
        if(result != null) {
            String message = messageSource.getMessage("auth.message." + result, null, null);
            return "redirect:/login.html?lang=en" + "&message=" + message;
        } else {
            //model.addAttribute("token", token);
            return "redirect:/updatePassword.html?lang=en";
        }
    }

    @RequestMapping(value = "/user/resetPassword",
            method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse resetPassword(HttpServletRequest request,
                                         @RequestParam("email") String userEmail) {
        UserResource user = resourceFinder.findUserByEmail(userEmail);
        if (user == null) {
            throw new UserNotFoundException();
        }
        String token = UUID.randomUUID().toString();
        service.createPasswordResetTokenForUser(user, token);
        mailSender.send(constructResetTokenEmail(getAppUrl(request),
                request.getLocale(), token, user));
        return new GenericResponse(messageSource.getMessage("message.resetPasswordEmail", null,request.getLocale()));
        //return new GenericResponse("You should receive an Password Reset Email shortly");

    }

    private SimpleMailMessage constructResendVerificationTokenEmail(final String contextPath, final Locale locale, final VerificationTokenEntity newToken, final UserResource user) {
        final String confirmationUrl = contextPath + "/registrationConfirm.html?token=" + newToken.getToken();
        final String message = messageSource.getMessage("message.resendToken", null, locale);
        return constructEmail("Resend Registration Token", message + " \r\n" + confirmationUrl, user);
    }

    private SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final UserResource user) {
        //final String url = contextPath + "/user/changePassword?id=" + user.getId() + "&token=" + token;
        final String url = contextPath + "/user/changePassword?token=" + token;
        final String message = messageSource.getMessage("message.resetPassword", null, locale);
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String subject, String body, UserResource user) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom(Objects.requireNonNull(messageSource.getMessage("message.email.from", null, null)));
        return email;
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + "8070" + request.getContextPath();
    }


}

