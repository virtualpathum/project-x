/**
 * Created On : 16 Aug 2017
 */
package com.lk.project.x.web.resource.finder;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lk.project.x.resource.AbstractResource;

// TODO: Auto-generated Javadoc
/**
 * The Interface ResourceFinder.
 * @author virtualpathum
 * @param <R> the generic type
 * @param <ID> the generic type
 */
public interface ResourceFinder <R extends AbstractResource<? extends Serializable>, ID extends Serializable> {
	
	/**
	 * Find one.
	 *
	 * @param id the id
	 * @return the r
	 */
	R findOne(ID id);
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<R> findAll();
	
	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<R> findAll(Pageable pageable);

	
}
