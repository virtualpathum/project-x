/**
 * Created On : 17 Aug 2017
 *//*

package com.lk.project.x.controller;

import java.util.List;

import javax.inject.Inject;


import com.lk.project.x.service.StudentService;
import com.lk.project.x.web.resource.finder.StudentResourceFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.lk.project.x.resource.StudentResource;

// TODO: Auto-generated Javadoc
*/
/**
 * @author virtualpathum
 * The Class StudentController.
 *//*

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class StudentController {

	*/
/** The Constant logger. *//*

	public static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	*/
/** The resource finder. *//*

	private StudentResourceFinder resourceFinder;

	*/
/** The service. *//*

	private StudentService service;

	*/
/**
	 * Instantiates a new student controller.
	 *
	 * @param resourceFinder the resource finder
	 * @param service the service
	 *//*

	@Inject
	public StudentController(StudentResourceFinder resourceFinder, StudentService service) {
		this.resourceFinder = resourceFinder;
		this.service = service;
	}

	*/
/**
	 * List all students.
	 *
	 * @return the list
	 *//*

	@RequestMapping(value = "/student/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<StudentResource> listAllStudents() {
		return resourceFinder.findAll();
	}

	*/
/**
	 * Gets the student.
	 *
	 * @param id the id
	 * @return the student resource
	 *//*

	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public StudentResource getStudent(@PathVariable("id") long id) {
		logger.info("Fetching Student with id {}", id);
		return resourceFinder.findOne(id);

	}

	*/
/**
	 * Creates the student.
	 *
	 * @param resource the resource
	 * @return the student resource
	 *//*

	@RequestMapping(value = "/student/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public StudentResource createStudent(@RequestBody StudentResource resource) {
		logger.info("Creating Student : {}", resource);
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

	@RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public StudentResource updateStudent(@PathVariable("id") long id, @RequestBody StudentResource resource) {
		logger.info("Updating Student with id {}", id);
		return service.saveOrUpdate(resource);
	}

	*/
/**
	 * Delete student.
	 *
	 * @param id the id
	 *//*

	@RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
	public void deleteStudent(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Student with id {}", id);
		service.delete(id);
	}


}*/
