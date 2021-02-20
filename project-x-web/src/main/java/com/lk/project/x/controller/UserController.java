
package com.lk.project.x.controller;

import com.lk.project.x.entity.UserEntity;
import com.lk.project.x.entity.VerificationTokenEntity;
import com.lk.project.x.exception.UserNotFoundException;
import com.lk.project.x.resource.UserResource;
import com.lk.project.x.service.UserService;
import com.lk.project.x.util.GenericResponse;
import com.lk.project.x.web.resource.finder.UserResourceFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.inject.Inject;
import javax.inject.Qualifier;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {
    /** The Constant logger. */
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /** The resource finder. */
    private UserResourceFinder resourceFinder;

    /** The service. */
    private UserService service;

    //@Inject
    private MessageSource messageSource;

    @Inject
    private JavaMailSender mailSender;

    @Inject
    private Environment env;

    /**
     * Instantiates a new student controller.
     *
     * @param resourceFinder the resource finder
     * @param service the service
     */
    //@Inject
    @Autowired
    public UserController(UserResourceFinder resourceFinder, UserService service, JavaMailSender mailSender, Environment env, MessageSource messageSource) {
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

    /**
     * Creates.
     *
     * @param resource the resource
     * @return the student resource
     */
    //@CrossOrigin(origins = "*", allowedHeaders = "*")
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public UserResource create(@RequestBody UserResource resource) {
        logger.info("Creating User : {}", resource);
        return service.saveOrUpdate(resource);

    }

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
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String confirmRegistration(@RequestParam("token") String token) {

        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
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

    @RequestMapping(value = "/user/resetPassword",
            method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse resetPassword(HttpServletRequest request,
                                         @RequestParam("email") String userEmail, @RequestHeader(value = "Authorization") String jwtToken) {
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

    /*@RequestMapping(value = "/user/changePassword",method = RequestMethod.POST)
    public String showChangePasswßordPage(Locale locale,
                                         @RequestParam("token") String token) {
        String result = service.validatePasswordResetToken(token);
        if(result != null) {
            String message = messages.getMessage("auth.message." + result, null, locale);
            return "redirect:/login.html?lang="
                    + locale.getLanguage() + "&message=" + message;
        } else {
            model.addAttribute("token", token);
            return "redirect:/updatePassword.html?lang=" + locale.getLanguage();
        }
    }*/

    private SimpleMailMessage constructResendVerificationTokenEmail(final String contextPath, final Locale locale, final VerificationTokenEntity newToken, final UserResource user) {
        final String confirmationUrl = contextPath + "/registrationConfirm.html?token=" + newToken.getToken();
        final String message = messageSource.getMessage("message.resendToken", null, locale);
        return constructEmail("Resend Registration Token", message + " \r\n" + confirmationUrl, user);
    }

    private SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final UserResource user) {
        final String url = contextPath + "/user/changePassword?id=" + user.getId() + "&token=" + token;
        final String message = messageSource.getMessage("message.resetPassword", null, locale);
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String subject, String body, UserResource user) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }


    /*@Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }*/
}

