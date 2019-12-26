/*
package com.lk.student.manager.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import StudentResource;
import com.lk.student.manager.service.StudentService;
import com.lk.student.manager.web.resource.finder.StudentResourceFinder;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	private StudentResourceFinder resourceFinder;

	private StudentService service;

	@Inject
	public RestApiController(StudentResourceFinder resourceFinder, StudentService service) {
		this.resourceFinder = resourceFinder;
		this.service = service;
	}

	@RequestMapping(value = "/student/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<StudentResource> listAllUsers() {
		return resourceFinder.findAllStudents();
	}

	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public StudentResource getUser(@PathVariable("id") long id) {
		logger.info("Fetching Student with id {}", id);
		return resourceFinder.findById(id);

	}

	@RequestMapping(value = "/student/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public StudentResource createUser(@RequestBody StudentResource resource, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Student : {}", resource);
		return service.saveOrUpdate(resource);

	}

	@RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public StudentResource updateUser(@PathVariable("id") long id, @RequestBody StudentResource resource) {
		logger.info("Updating Student with id {}", id);
		return service.saveOrUpdate(resource);
	}

	@RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Student with id {}", id);
		service.delete(id);

	}

*/
/*	@RequestMapping(value = "/student/", method = RequestMethod.DELETE)
	public void deleteAllUsers() {
		logger.info("Deleting All Students");
		service.deleteAllStudents();
	}*//*


}*/
