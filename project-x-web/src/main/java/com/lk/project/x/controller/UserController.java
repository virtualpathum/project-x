
package com.lk.project.x.controller;

import com.lk.project.x.resource.UserResource;
import com.lk.project.x.service.UserService;
import com.lk.project.x.web.resource.finder.UserResourceFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

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

    /**
     * Instantiates a new student controller.
     *
     * @param resourceFinder the resource finder
     * @param service the service
     */
    @Inject
    public UserController(UserResourceFinder resourceFinder, UserService service) {
        this.resourceFinder = resourceFinder;
        this.service = service;
    }

    /**
     * List all students.
     *
     * @return the list
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/user/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<UserResource> listAll() {
        logger.info("Fetching Users");
        return resourceFinder.findAll();
    }



    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserResource get(@PathVariable("id") long id) {
        logger.info("Fetching User with id {}", id);
        return resourceFinder.findOne(id);

    }

    /**
     * Creates the student.
     *
     * @param resource the resource
     * @return the student resource
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public UserResource create(@RequestBody UserResource resource) {
        logger.info("Creating User : {}", resource);
        return service.saveOrUpdate(resource);

    }

    /**
     * Update student.
     *
     * @param id the id
     * @param resource the resource
     * @return the student resource
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public UserResource update(@PathVariable("id") long id, @RequestBody UserResource resource) {
        logger.info("Updating User with id {}", id);
        return service.saveOrUpdate(resource);
    }

    /**
     * Delete student.
     *
     * @param id the id
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting User with id {}", id);
        service.delete(id);
    }

}

