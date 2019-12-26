/**
 * Created On : 17 Aug 2017
 */
package com.lk.project.x.service;

import com.lk.project.x.resource.StudentResource;


// TODO: Auto-generated Javadoc
/**
 * The Interface StudentService.
 * @author virtualpathum
 */
public interface StudentService {
	
	/**
	 * Save or update.
	 *
	 * @param resource the resource
	 * @return the student resource
	 */
	StudentResource saveOrUpdate (StudentResource resource);
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	void delete(Long id);
}