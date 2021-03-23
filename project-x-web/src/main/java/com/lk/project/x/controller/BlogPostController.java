/*
package com.lk.project.x.controller;

import com.lk.project.x.resource.BlogPostResource;
import com.lk.project.x.service.BlogPostService;
import com.lk.project.x.web.resource.finder.BlogPostResourceFinder;
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
public class BlogPostController {

    */
/** The Constant logger. *//*

    public static final Logger logger = LoggerFactory.getLogger(BlogPostController.class);

    */
/** The resource finder. *//*

    private BlogPostResourceFinder resourceFinder;

    */
/** The service. *//*

    private BlogPostService service;

    */
/**
     * Instantiates a new student controller.
     *
     * @param resourceFinder the resource finder
     * @param service the service
     *//*

    @Inject
    public BlogPostController(BlogPostResourceFinder resourceFinder, BlogPostService service) {
        this.resourceFinder = resourceFinder;
        this.service = service;
    }

    */
/**
     * List all students.
     *
     * @return the list
     *//*

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/blog/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<BlogPostResource> listAll() {
        logger.info("Fetching Blog Posts");
        return resourceFinder.findAll();
    }



    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/blog/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BlogPostResource get(@PathVariable("id") long id) {
        logger.info("Fetching Blog Posts with id {}", id);
        return resourceFinder.findOne(id);

    }

    */
/**
     * Creates the student.
     *
     * @param resource the resource
     * @return the student resource
     *//*

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/blog/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPostResource create(@RequestBody BlogPostResource resource) {
        logger.info("Creating Blog Post : {}", resource);
        return service.saveOrUpdate(resource);

    }

    */
/**
     * Update student.
     *
     * @param id the id
     * @param resource the resource
     * @return the student resource
     *//*

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/blog/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public BlogPostResource update(@PathVariable("id") long id, @RequestBody BlogPostResource resource) {
        logger.info("Updating Blog Post with id {}", id);
        return service.saveOrUpdate(resource);
    }

    */
/**
     * Delete student.
     *
     * @param id the id
     *//*

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/blog/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") long id) {
        logger.info("Deleting Blog Posts with id {}", id);
        service.delete(id);
    }
}
*/
